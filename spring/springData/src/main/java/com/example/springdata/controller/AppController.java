package com.example.springdata.controller;

import com.example.springdata.entity.Product;
import com.example.springdata.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;

@Controller
public class AppController {
    @Autowired
    ProductService productService;
    @RequestMapping("/")
    public String allProducts(Model model){
        model.addAttribute("products", productService.getProducts());
        return "index";
    }
    @RequestMapping("/add-pro")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
       productService.removeProductById(id);
        return "redirect:/";
    }

    @RequestMapping("/show-update/{id}")
    public String showUpdateProductForm(Model model,@PathVariable Long id){
        Product product=productService.getProductById(id);
        model.addAttribute("product",product);
        return "update-product";
    }
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product) {
        productService.updateProduct(product);
        return "redirect:/";
    }

//    @RequestMapping("/test")
//    public String test(@RequestParam) {
//        return "test";
//    }
}
