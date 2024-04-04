package onlineorder.laioffer.onlineorder.service;

import onlineorder.laioffer.onlineorder.entity.MenuItemEntity;
import onlineorder.laioffer.onlineorder.entity.RestaurantEntity;
import onlineorder.laioffer.onlineorder.model.MenuItemDto;
import onlineorder.laioffer.onlineorder.model.RestaurantDto;
import onlineorder.laioffer.onlineorder.repository.MenuItemRepository;
import onlineorder.laioffer.onlineorder.repository.RestaurantRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestaurantService {


    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;


    public RestaurantService(
            RestaurantRepository restaurantRepository,
            MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Cacheable("restaurant")
    public List<RestaurantDto> getRestaurants() {
        List<RestaurantEntity> restaurantEntities = restaurantRepository.findAll();
        List<MenuItemEntity> menuItemEntities = menuItemRepository.findAll();
        Map<Long, List<MenuItemDto>> groupedMenuItems = new HashMap<>();
        for (MenuItemEntity menuItemEntity : menuItemEntities) {
            List<MenuItemDto> group = groupedMenuItems.computeIfAbsent(menuItemEntity.restaurantId(), k -> new ArrayList<>());
            MenuItemDto menuItemDto = new MenuItemDto(menuItemEntity);
            group.add(menuItemDto);
        }
        List<RestaurantDto> results = new ArrayList<>();
        for (RestaurantEntity restaurantEntity : restaurantEntities) {
            RestaurantDto restaurantDto = new RestaurantDto(restaurantEntity, groupedMenuItems.get(restaurantEntity.id()));
            results.add(restaurantDto);
        }
        return results;
    }
}

