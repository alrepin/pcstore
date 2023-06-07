package ga.repin.easybot.pcstore.controller;

import ga.repin.easybot.pcstore.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;


@RestController
@CrossOrigin()
@RequestMapping("${ept.prod}")
@RequiredArgsConstructor
public class ProductController {
    

    private final ProductService productService;
    
    
    @Operation(
            tags = "products",
            summary = "Adding new product"
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody LinkedHashMap<String, String> hashMap) {
        try {
            productService.create(hashMap);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @Operation(
            tags = "products",
            summary = "Update product"
    )
    @PatchMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody LinkedHashMap<String, String> hashMap) {
        try {
            productService.update(id, hashMap);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @Operation(
            tags = "products",
            summary = "Get product by id"
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> get(@PathVariable long id) {
        try {
            LinkedHashMap<String, String> hashMap = productService.get(id);
            return ResponseEntity.ok(hashMap);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Operation(
            tags = "products",
            summary = "Get list products by type"
    )
    @GetMapping(value = "/findbytype/{product_type}")
    public ResponseEntity<?> get(@PathVariable String product_type) {
        try {
            ArrayList<LinkedHashMap<String, String>> productList = productService.get(product_type);
            return ResponseEntity.ok(productList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }
    
}