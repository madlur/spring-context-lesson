package ru.geekbrains.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
public class Cart {

    private List<Product> productList = new ArrayList<>();

    ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void add(long id) {
        productList.add(productRepository.findById(id));
    }

    public void delete(long id) {
        productList.removeIf(product -> product.getId() == id);
    }

    public void showcart() {
        productList = productList.stream().sorted(Comparator.comparingLong(Product::getId)).collect(Collectors.toList());
        for (Product product : productList) {
            System.out.println(product);
        }
    }
}

