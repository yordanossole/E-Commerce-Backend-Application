package com.yordanos.dreamShops.repository;

import com.yordanos.dreamShops.model.Cart;
import com.yordanos.dreamShops.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);

    Long user(User user);
}
