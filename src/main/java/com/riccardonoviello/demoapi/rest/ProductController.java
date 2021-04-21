package com.riccardonoviello.demoapi.rest;

import com.riccardonoviello.demoapi.entity.Product;
import com.riccardonoviello.demoapi.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // endpoints
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        log.info("getProducts() called");
        List<ProductResponse> products = fetchProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        log.info("getProduct({}) called", id);
        Optional<ProductResponse> product = fetchProduct(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }

    // utility methods for the sake of this demo project

    @PostConstruct
    public void initialiseProducts() {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().id(1L).name("Mp3 Player").price(BigDecimal.valueOf(15.00)).build());
        products.add(Product.builder().id(2L).name("Headphones").price(BigDecimal.valueOf(20.00)).build());
        products.add(Product.builder().id(3L).name("Keyboard").price(BigDecimal.valueOf(9.50)).build());
        products.add(Product.builder().id(4L).name("Mouse").price(BigDecimal.valueOf(6.10)).build());
        products.add(Product.builder().id(5L).name("Webcam").price(BigDecimal.valueOf(25.80)).build());
        log.info("Products initialised");
        productRepository.saveAll(products);
    }

    private List<ProductResponse> fetchProducts() {
        return productRepository.findAll().stream()
                .map(item -> ProductResponse.builder().id(item.getId()).name(item.getName()).price(item.getPrice()).build())
                .collect(Collectors.toList());
    }

    private Optional<ProductResponse> fetchProduct(Long id) {
        return fetchProducts().stream().filter(p -> p.getId().equals(id)).findFirst();
    }

}
