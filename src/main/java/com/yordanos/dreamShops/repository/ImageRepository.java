package com.yordanos.dreamShops.repository;

import com.yordanos.dreamShops.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
