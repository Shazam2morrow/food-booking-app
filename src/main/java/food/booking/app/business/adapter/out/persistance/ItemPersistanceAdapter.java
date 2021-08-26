package food.booking.app.business.adapter.out.persistance;

import food.booking.app.business.app.port.out.group.LoadGroupItemSlicePort;
import food.booking.app.business.app.port.out.item.*;
import food.booking.app.business.domain.Item;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

/**
 * Item persistance adapter
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
class ItemPersistanceAdapter implements CreateItemPort,
        CheckItemSlugPort,
        LoadGroupItemSlicePort,
        LoadItemDetailsPort,
        UpdateItemDetailsPort {

    private final ItemRepository itemRepository;

    private final ItemPersistanceMapper itemPersistanceMapper;

    private final ItemPersistanceResolver itemPersistanceResolver;

    @Override
    @Transactional
    public Item create(CreateItem createItem) {
        ItemJpaEntity item = itemPersistanceMapper.mapToJpaEntity(requireValid(createItem));
        return itemPersistanceMapper.mapToDomainEntity(itemRepository.save(item));
    }

    @Override
    @Transactional
    public void update(UpdateItemDetails details) {
        ItemJpaEntity item = itemPersistanceResolver.resolve(requireValid(details));
        itemPersistanceMapper.applyUpdatedDetails(item, details);
    }

    @Override
    public boolean checkBySlug(String itemSlug) {
        return itemRepository.existsBySlug(requireValidSlug(itemSlug));
    }

    @Override
    public Item loadDetailsBySlug(String itemSlug) {
        ItemJpaEntity item = itemPersistanceResolver.resolve(requireValidSlug(itemSlug));
        return itemPersistanceMapper.mapToDomainEntity(item);
    }

    @Override
    public Slice<Item> loadSliceByGroupSlug(String groupSlug, Pageable page) {
        return itemRepository.findAllByGroupSlugAndOrderBySortOrder(requireValidSlug(groupSlug), requireValidPage(page))
                .map(itemPersistanceMapper::mapToDomainEntity);
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
