package com.example.booksstory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class BooksStoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooksStoryApplication.class, args);
    }

}
