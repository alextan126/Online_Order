package onlineorder.laioffer.onlineorder.controller;

import onlineorder.laioffer.onlineorder.entity.MenuItemEntity;
import onlineorder.laioffer.onlineorder.model.RestaurantDto;
import onlineorder.laioffer.onlineorder.service.MenuItemService;
import onlineorder.laioffer.onlineorder.service.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {


    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;


    public MenuController(RestaurantService restaurantService, MenuItemService menuItemService) {
        this.restaurantService = restaurantService;
        this.menuItemService = menuItemService;
    }


    @GetMapping("/restaurant/{restaurantId}/menu")
    public List<MenuItemEntity> getMenuByRestaurant(@PathVariable("restaurantId")  long restaurantId) {
        return menuItemService.getMenuItemsByRestaurantId(restaurantId);
    }


    @GetMapping("/restaurants/menu")
    public List<RestaurantDto> getMenuForAllRestaurants() {
        return restaurantService.getRestaurants();
    }
}
