package com.example.springdata.service;

import com.example.springdata.entity.Product;
import com.example.springdata.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public void removeProductById(Long id){
        productRepository.deleteById(id);
    }
    public void removeProduct(Product product){
        productRepository.delete(product);
    }
    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }
    public void updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId()).orElse(null);

        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setBrand(product.getBrand());
            existingProduct.setCategory(product.getCategory());
            productRepository.save(existingProduct);
        } else {
            System.err.println();
        }
    }

}
