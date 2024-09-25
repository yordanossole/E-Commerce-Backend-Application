package com.yordanos.dreamShops.controller;

import com.yordanos.dreamShops.exceptions.ResourceNotFoundException;
import com.yordanos.dreamShops.model.Product;
import com.yordanos.dreamShops.request.AddProductRequest;
import com.yordanos.dreamShops.request.ProductUpdateRequest;
import com.yordanos.dreamShops.response.ApiResponse;
import com.yordanos.dreamShops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prifix}/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse("Found!", products));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(new ApiResponse("Found!", product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/product/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest addProductRequest) {
        try {
            Product product = productService.addProduct(addProductRequest);
            return ResponseEntity.ok(new ApiResponse("Added!", product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/product/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequest productUpdateRequest) {
        try {
            Product product = productService.updateProduct(productUpdateRequest, productId);
            return ResponseEntity.ok(new ApiResponse("Updated!", product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/product/delete/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Deleted!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/brand/{brand}")
    public ResponseEntity<ApiResponse> getProductByBrand(@PathVariable String brand) {
        try {
            List<Product> products = productService.getProductsByBrand(brand);
            return ResponseEntity.ok(new ApiResponse("Found!", products));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/category/{categoryId}")
    public ResponseEntity<ApiResponse> getProductByCategory(@PathVariable String categoryName) {
        try {
            List<Product> products = productService.getProductsByCategoryId(categoryName);
            return ResponseEntity.ok(new ApiResponse("Found!", products));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/category-and-brand/")


    public void ad() {
        productService.
    }


}
