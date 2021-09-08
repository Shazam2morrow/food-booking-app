package food.booking.app.business.app;

import food.booking.app.business.app.port.in.group.LoadGroupItemSliceUseCase;
import food.booking.app.business.app.port.in.item.*;
import food.booking.app.business.app.port.in.item.exception.ItemServiceException;
import food.booking.app.business.app.port.out.group.LoadGroupItemSlicePort;
import food.booking.app.business.app.port.out.item.*;
import food.booking.app.business.domain.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

/**
 * Menu item service
 *
 * @author shazam2morrow
 */
@Slf4j
@RequiredArgsConstructor
class ItemService implements CreateItemUseCase,
        LoadGroupItemSliceUseCase,
        LoadItemDetailsUseCase,
        UpdateItemDetailsUseCase,
        DeleteItemUseCase {

    private final CreateItemPort createItemPort;

    private final ItemServiceMapper itemServiceMapper;

    private final LoadItemDetailsPort loadItemDetailsPort;

    private final UpdateItemDetailsPort updateItemDetailsPort;

    private final LoadGroupItemSlicePort loadGroupItemSlicePort;

    @Override
    @Transactional
    public Item create(CreateItemCommand command) {
        log.debug("Trying to create item: {}", command);
        try {
            CreateItem createItem = itemServiceMapper.mapToCreateMenuItem(requireValid(command));
            return createItemPort.create(createItem);
        } catch (Exception ex) {
            throw new ItemServiceException("Failed to create item!", ex);
        }
    }

    @Override
    @Transactional
    public void update(UpdateItemDetailsCommand command) {
        log.debug("Trying to update item details: {}", command);
        try {
            UpdateItemDetails details = itemServiceMapper.mapToUpdateItemDetails(requireValid(command));
            updateItemDetailsPort.update(details);
        } catch (Exception ex) {
            throw new ItemServiceException("Failed to update item details!", ex);
        }
    }

    @Override
    @Transactional
    public void deleteBySlug(String itemSlug) {
        log.debug("Trying to delete item: {}", itemSlug);
        Item item = loadDetailsBySlug(itemSlug);
        if (item.isActive()) {
            item.setActive(Boolean.FALSE);
            update(itemServiceMapper.mapToUpdateItemDetailsCommand(item));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<Item> loadSliceByGroupSlug(String groupSlug, Pageable page) {
        log.debug("Trying to load slice of group items: {}, {}", groupSlug, page);
        return loadGroupItemSlicePort.loadSliceByGroupSlug(requireValidSlug(groupSlug), requireValidPage(page));
    }

    @Override
    @Transactional(readOnly = true)
    public Item loadDetailsBySlug(String itemSlug) {
        log.debug("Trying to load item details: {}", itemSlug);
        return loadItemDetailsPort.loadDetailsBySlug(requireValidSlug(itemSlug));
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
     * Validate page
     *
     * @param page page
     * @return validated page
     */
    private Pageable requireValidPage(Pageable page) {
        return Validate.notNull(page, "page can not be null");
    }

    /**
     * Validate object
     *
     * @param command update item details command
     * @return validated object
     */
    private UpdateItemDetailsCommand requireValid(UpdateItemDetailsCommand command) {
        return Validate.notNull(command, "updateItemDetailsCommand can not be null");
    }

    /**
     * Validate object
     *
     * @param command create item command
     * @return validated object
     */
    private CreateItemCommand requireValid(CreateItemCommand command) {
        return Validate.notNull(command, "createItemCommand can not be null");
    }

}
