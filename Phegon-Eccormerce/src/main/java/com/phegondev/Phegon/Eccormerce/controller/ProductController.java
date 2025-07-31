package com.phegondev.Phegon.Eccormerce.controller;

import com.phegondev.Phegon.Eccormerce.dto.Response;
import com.phegondev.Phegon.Eccormerce.exception.InvalidCredentialsException;
import com.phegondev.Phegon.Eccormerce.service.interf.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createProduct(
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("image") MultipartFile image,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") BigDecimal price
    ){
        if (categoryId == null || image.isEmpty() || name.isEmpty() || description.isEmpty() || price == null){
            throw new InvalidCredentialsException("All Fields are Required");
        }
        return ResponseEntity.ok(productService.createProduct(categoryId, image, name, description, price));
    }


    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateProduct(
            @RequestParam("productId") Long productId,
            @RequestParam(required = false, name = "categoryId") Long categoryId,
            @RequestParam(required = false, name = "image")  MultipartFile image,
            @RequestParam(required = false, name = "name")  String name,
            @RequestParam(required = false, name = "description")  String description,
            @RequestParam(required = false, name = "price")  BigDecimal price
    ){
        return ResponseEntity.ok(productService.updateProduct(productId, categoryId, image, name, description, price));
    }

    @DeleteMapping("/delete/{productId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteProduct(@PathVariable("productId") Long productId){
        return ResponseEntity.ok(productService.deleteProduct(productId));

    }


    @GetMapping("/get-by-product-id/{productId}")
    public ResponseEntity<Response> getProductById(@PathVariable("productId") Long productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Response> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }


    @GetMapping("/get-by-category-id/{categoryId}")
    public ResponseEntity<Response> getProductsByCategory(@PathVariable("categoryId") Long categoryId){
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }

    @GetMapping("/search")
    public ResponseEntity<Response> searchForProduct(@RequestParam("searchValue") String searchValue){
        return ResponseEntity.ok(productService.searchProduct(searchValue));
    }


}
