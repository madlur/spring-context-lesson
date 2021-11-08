package ru.geekbrains.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainApp {

    private static Logger logger = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.geekbrains.context");
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        String command;
        ProductRepository productRepository = context.getBean("produktik", ProductRepository.class);
        try {
            while (!(command = buff.readLine()).equals("exit")) {
                if (command.equals("show")) {
                    System.out.println("Введите ID продукта для вывода его на экран ");
                    long id = Long.parseLong(buff.readLine());
                    System.out.println(productRepository.findById(id));
                }
                if (command.equals("get all")) {
                    List<Product> products = productRepository.findAllProducts();
                    for (Product p : products) {
                        System.out.println(p);
                    }

                }
                if (command.equals("add cart")) {
                    System.out.println("Введите ID продукта для добавления в корзину");
                    long id = Long.parseLong(buff.readLine());
                    Cart cart = context.getBean(Cart.class);
                    cart.add(id);
                    cart.showcart();

                }

                if (command.equals("delete cart")) {
                    System.out.println("Введите ID продукта для удаления");
                    long id = Long.parseLong(buff.readLine());
                    Cart cart = context.getBean(Cart.class);
                    cart.delete(id);
                    cart.showcart();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        context.close();
    }

}
