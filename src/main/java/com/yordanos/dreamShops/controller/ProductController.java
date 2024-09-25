package com.yordanos.dreamShops.controller;

import com.yordanos.dreamShops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prifix}/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping()

}
