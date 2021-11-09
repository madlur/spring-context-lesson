package ru.geekbrains.context;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component(value = "produktik")
public class ProductRepository {

    private List<Product> products;


    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            products.add(new Product((long) i + 1, "Product #" + i + 1,
                    BigDecimal.valueOf(Math.random() * 1000).setScale(2, RoundingMode.HALF_UP)));
        }
    }

    public Product findById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst()
                .orElseThrow(() -> new RuntimeException());
    }

    public List<Product> findAllProducts() {
        return Collections.unmodifiableList(products);
    }
}
