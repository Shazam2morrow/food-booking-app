package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.in.item.exception.ItemNotFoundException;
import food.booking.app.shared.HasSlug;
import lombok.RequiredArgsConstructor;

/**
 * Item persistence resolver
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class ItemPersistenceResolver {

    private final ItemRepository itemRepository;

    /**
     * Resolve item jpa entity
     *
     * @param hasSlug slugable
     * @return resolved item jpa entity
     * @throws ItemNotFoundException if item was not found
     */
    ItemJpaEntity resolve(HasSlug<String> hasSlug) {
        return resolve(hasSlug.getSlug());
    }

    /**
     * Resolve item jpa entity
     *
     * @param itemSlug item slug
     * @return resolved item jpa entity
     * @throws ItemNotFoundException if item was not found
     */
    ItemJpaEntity resolve(String itemSlug) {
        return itemRepository.findBySlug(itemSlug)
                .orElseThrow(() -> new ItemNotFoundException(itemSlug));
    }

}
