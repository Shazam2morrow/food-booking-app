package food.booking.app.business.adapter.out.persistance;

import food.booking.app.business.app.port.out.item.UpdateItemDetails;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;

import javax.persistence.EntityNotFoundException;

/**
 * Item persistance resolver
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class ItemPersistanceResolver {

    private final ItemRepository itemRepository;

    ItemJpaEntity resolve(UpdateItemDetails updateItemDetails) {
        return resolve(requireValid(updateItemDetails).slug());
    }

    ItemJpaEntity resolve(String itemSlug) {
        return itemRepository.findBySlug(requireValidSlug(itemSlug))
                .orElseThrow(() -> new EntityNotFoundException("Item was not found " + itemSlug));
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
     * Validate object
     *
     * @param details update item details
     * @return validated object
     */
    private UpdateItemDetails requireValid(UpdateItemDetails details) {
        return Validate.notNull(details, "updateItemDetails can not be null");
    }

}
