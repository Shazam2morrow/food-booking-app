package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.out.group.LoadGroupItemSlicePort;
import food.booking.app.business.app.port.out.item.*;
import food.booking.app.business.domain.Item;
import lombok.RequiredArgsConstructor;
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
        ItemJpaEntity item = itemPersistenceMapper.mapToJpaEntity(createItem);
        return itemPersistenceMapper.mapToDomainEntity(itemRepository.save(item));
    }

    @Override
    @Transactional
    public void update(UpdateItemDetails details) {
        itemPersistenceMapper.applyUpdatedDetails(details);
    }

    @Override
    public boolean checkBySlug(String itemSlug) {
        return itemRepository.existsBySlug(itemSlug);
    }

    @Override
    public Item loadDetailsBySlug(String itemSlug) {
        ItemJpaEntity item = itemPersistenceResolver.resolve(itemSlug);
        return itemPersistenceMapper.mapToDomainEntity(item);
    }

    @Override
    public Slice<Item> loadSliceByGroupSlug(String groupSlug, Pageable page) {
        return itemRepository.findAllByGroupSlugAndOrderBySortOrder(groupSlug, page)
                .map(itemPersistenceMapper::mapToDomainEntity);
    }

}
