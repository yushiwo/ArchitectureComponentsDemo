package com.example.netease.architecturecomponentsdemo.demo.model.impl;

import com.example.netease.architecturecomponentsdemo.demo.model.Product;
import com.example.netease.architecturecomponentsdemo.demo.model.dto.ProductDto;

/**
 * Created by hzzhengrui on 17/11/21.
 */

public class ProductImpl implements Product {
    private String name;
    private float weight;

    public ProductImpl(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    public ProductImpl(ProductDto productDto) {
        if (productDto == null) {
            return;
        }

        this.name = productDto.getProduct().getName();
        this.weight = productDto.getProduct().getWeight();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getWeight() {
        return weight;
    }
}
