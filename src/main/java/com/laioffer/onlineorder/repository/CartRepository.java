package com.laioffer.onlineorder.repository;

import com.laioffer.onlineorder.entity.CartEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface CartRepository extends ListCrudRepository<CartEntity, Long> {
    CartEntity getByCustomerId(Long customerId);

    @Modifying
    @Query("UPDATE carts SET total_price = :totalPrice where id = :cartId")
    void updateTotalPrice(Long cartId, Double totalPrice);
}

