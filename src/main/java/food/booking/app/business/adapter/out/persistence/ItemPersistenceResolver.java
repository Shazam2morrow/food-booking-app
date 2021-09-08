package food.booking.app.business.adapter.out.persistence;

import food.booking.app.business.app.port.in.item.exception.ItemNotFoundException;
import food.booking.app.shared.Slugable;
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
     * @param slugable slugable
     * @return resolved item jpa entity
     * @throws ItemNotFoundException if item was not found
     */
    ItemJpaEntity resolve(Slugable<String> slugable) {
        return resolve(slugable.getSlug());
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
