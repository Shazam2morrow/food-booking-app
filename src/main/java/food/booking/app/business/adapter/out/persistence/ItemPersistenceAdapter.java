package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.out.group.LoadGroupItemSlicePort;
import food.booking.app.business.app.port.out.item.*;
import food.booking.app.business.domain.Item;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

/**
 * Item persistence adapter
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
class ItemPersistenceAdapter implements CreateItemPort,
        CheckItemSlugPort,
        LoadGroupItemSlicePort,
        LoadItemDetailsPort,
        UpdateItemDetailsPort {

    private final ItemRepository itemRepository;

    private final ItemPersistenceMapper itemPersistenceMapper;

    private final ItemPersistenceResolver itemPersistenceResolver;

    @Override
    @Transactional
    public Item create(CreateItem createItem) {
        ItemJpaEntity item = itemPersistenceMapper.mapToJpaEntity(requireValid(createItem));
        return itemPersistenceMapper.mapToDomainEntity(itemRepository.save(item));
    }

    @Override
    @Transactional
    public void update(UpdateItemDetails details) {
        ItemJpaEntity item = itemPersistenceResolver.resolve(requireValid(details));
        itemPersistenceMapper.applyUpdatedDetails(item, details);
    }

    @Override
    public boolean checkBySlug(String itemSlug) {
        return itemRepository.existsBySlug(requireValidSlug(itemSlug));
    }

    @Override
    public Item loadDetailsBySlug(String itemSlug) {
        ItemJpaEntity item = itemPersistenceResolver.resolve(requireValidSlug(itemSlug));
        return itemPersistenceMapper.mapToDomainEntity(item);
    }

    @Override
    public Slice<Item> loadSliceByGroupSlug(String groupSlug, Pageable page) {
        return itemRepository.findAllByGroupSlugAndOrderBySortOrder(requireValidSlug(groupSlug), requireValidPage(page))
                .map(itemPersistenceMapper::mapToDomainEntity);
    }

    /**
     * Validate object
     *
     * @param createItem create item
     * @return validated object
     */
    private CreateItem requireValid(CreateItem createItem) {
        return Validate.notNull(createItem, "createItem can not be null");
    }

    /**
     * Validate object
     *
     * @param itemDetails update item details
     * @return validate object
     */
    private UpdateItemDetails requireValid(UpdateItemDetails itemDetails) {
        return Validate.notNull(itemDetails, "updateItemDetails can not be null");
    }

    /**
     * Validate slug
     *
     * @param slug slug
     * @return validated slug
     */
    private String requireValidSlug(String slug) {
        return Validate.notBlank(slug, "slug can not be blank");
    }

    /**
     * Validated page
     *
     * @param page page
     * @return validate page
     */
    private Pageable requireValidPage(Pageable page) {
        return Validate.notNull(page, "pageable can not be null");
    }

}
