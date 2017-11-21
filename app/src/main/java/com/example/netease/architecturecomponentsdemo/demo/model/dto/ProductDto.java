package com.example.netease.architecturecomponentsdemo.demo.model.dto;

import com.example.netease.architecturecomponentsdemo.demo.model.Product;

/**
 * Created by hzzhengrui on 17/11/21.
 */

public class ProductDto {
    private boolean error;
    private Product product;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
