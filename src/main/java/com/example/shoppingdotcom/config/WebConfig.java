package com.example.shoppingdotcom.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve files from /uploads/profile_img/ as /profile_img/** URLs
        registry.addResourceHandler("/profile_img/**")
                .addResourceLocations("file:" + System.getProperty("user.home") + "/uploads/profile_img/");

        // Serve files from /uploads/product_img/ as /product_img/** URLs
        registry.addResourceHandler("/product_img/**")
                .addResourceLocations("file:" + System.getProperty("user.home") + "/uploads/product_img/");

        // Serve files from /uploads/category_img/ as /category_img/** URLs
        registry.addResourceHandler("/category_img/**")
                .addResourceLocations("file:" + System.getProperty("user.home") + "/uploads/category_img/");
    }
}
