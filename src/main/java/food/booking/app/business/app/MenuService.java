package food.booking.app.business.app;

import food.booking.app.business.app.port.in.menu.*;
import food.booking.app.business.app.port.in.menu.exception.MenuServiceException;
import food.booking.app.business.app.port.out.menu.*;
import food.booking.app.business.domain.Menu;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Menu service
 *
 * @author shazam2morrow
 */
@Slf4j
@Validated
@RequiredArgsConstructor
class MenuService implements CreateMenuUseCase,
        LoadRestaurantMenuListUseCase,
        UpdateMenuDetailsUseCase,
        LoadMenuDetailsUseCase,
        DeleteMenuUseCase {

    private final CreateMenuPort createMenuPort;

    private final MenuServiceMapper menuServiceMapper;

    private final LoadMenuDetailsPort loadMenuDetailsPort;

    private final UpdateMenuDetailsPort updateMenuDetailsPort;

    private final LoadRestaurantMenuListPort loadRestaurantMenuListPort;

    @Override
    @Transactional
    public Menu create(CreateMenuCommand command) {
        log.debug("Trying to create menu: {}", command);
        try {
            CreateMenu menu = menuServiceMapper.mapToCreateMenu(requireValid(command));
            return createMenuPort.create(menu);
        } catch (Exception ex) {
            throw new MenuServiceException("Failed to create menu!", ex);
        }
    }

    @Override
    @Transactional
    public void update(UpdateMenuDetailsCommand command) {
        log.debug("Trying to update menu details: {}", command);
        try {
            UpdateMenuDetails details = menuServiceMapper.mapToUpdateMenuDetails(requireValid(command));
            updateMenuDetailsPort.update(details);
        } catch (Exception ex) {
            throw new MenuServiceException("Failed to update menu details!", ex);
        }
    }

    @Override
    @Transactional
    public void deleteBySlug(String menuSlug) {
        log.debug("Trying to delete menu: {}", menuSlug);
        Menu menu = loadDetailsBySlug(menuSlug);
        if (menu.isActive()) {
            menu.setActive(Boolean.FALSE);
            update(menuServiceMapper.mapToUpdateMenuDetailsCommand(menu));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Menu> loadRestaurantMenuList(String restaurantSlug) {
        log.debug("Trying to load list of restaurant menus: {}", restaurantSlug);
        return loadRestaurantMenuListPort.loadRestaurantMenuList(requireValidSlug(restaurantSlug));
    }

    @Override
    @Transactional(readOnly = true)
    public Menu loadDetailsBySlug(String menuSlug) {
        log.debug("Trying to load menu details: {}", menuSlug);
        return loadMenuDetailsPort.loadDetailsBySlug(requireValidSlug(menuSlug));
    }

    /**
     * Validate object
     *
     * @param command create menu command
     * @return validated object
     */
    private CreateMenuCommand requireValid(CreateMenuCommand command) {
        return Validate.notNull(command, "createMenuCommand can not be null");
    }

    /**
     * Validate object
     *
     * @param command update menu details command
     * @return validated object
     */
    private UpdateMenuDetailsCommand requireValid(UpdateMenuDetailsCommand command) {
        return Validate.notNull(command, "updateMenuDetailsCommand can not be null");
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

}
