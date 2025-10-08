package br.com.amisahdev.spring_pilot.api.product.controllers;

import br.com.amisahdev.spring_pilot.api.product.Product;
import jakarta.websocket.server.PathParam;
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

    @GetMapping("")
    public ResponseEntity<ArrayList<Product>> getProducts(){
       return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product newProduct = new Product(fakeID.incrementAndGet(),  product.getName(), product.getPrice(), product.getQuantity());
        products.add(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> detailProduct(@PathVariable() long id){
        for(Product product : products){
            if(product.getId() == id){
                return ResponseEntity.status(HttpStatus.OK).body(product);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    };


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product){
        for(Product productAux : products){
            if(productAux.getId() == id){
                productAux.setName(product.getName() );
                productAux.setPrice(productAux.getPrice());
                productAux.setQuantity(productAux.getQuantity());
                return ResponseEntity.status(HttpStatus.OK).body(productAux);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    };

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id){
        try{
            boolean removed = products.removeIf(product -> product.getId() == id);
            if (removed){
                return ResponseEntity.status(HttpStatus.OK).body("Product with id " + id + " was deleted successfully");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with id " + id + " was not found");
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    };

    @GetMapping("/search")
    public ResponseEntity<Product> searchProduct(@RequestParam String name){
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
