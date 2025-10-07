package br.com.amisahdev.spring_pilot.api.product.controllers;

import br.com.amisahdev.spring_pilot.api.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import br.com.amisahdev.spring_pilot.api.product.Product;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final AtomicLong fakeID = new AtomicLong();
    private final ArrayList<Product> products =  new ArrayList<>();

    @GetMapping("/")
    public ResponseEntity<ArrayList<Product>> getProducts(){
       return  ResponseEntity.ok(products);
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product newProduct = new Product(fakeID.incrementAndGet(),  product.getName(), product.getPrice(), product.getQuantity());
        products.add(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @GetMapping("/search")
    public ResponseEntity<Product> searchProduct(@RequestParam String name){
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
