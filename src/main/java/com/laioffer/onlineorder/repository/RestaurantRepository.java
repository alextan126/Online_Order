package onlineorder.laioffer.onlineorder.repository;

import onlineorder.laioffer.onlineorder.entity.RestaurantEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface RestaurantRepository extends ListCrudRepository<RestaurantEntity, Long> {
}
