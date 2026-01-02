package ua.house.book.productservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.house.book.productservice.domain.dto.request.ListProductDTO;
import ua.house.book.productservice.domain.dto.request.ProductDTO;
import ua.house.book.productservice.service.ProductService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/core/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create-product")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductDTO productDTORequest) {
        productService.createProduct(productDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("Product create " + productDTORequest + " is successful");
    }

    @PutMapping("/update-product")
    public ResponseEntity<String> updateProduct(@Valid @RequestBody ProductDTO productDTORequest) {
        productService.updateProduct(productDTORequest);
        return ResponseEntity.ok()
                             .body("Product update " + productDTORequest + " is successful");
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<String> deleteProduct(@Valid @RequestBody ProductDTO productDTORequest) {

        productService.deleteProduct(productDTORequest);
        return ResponseEntity.ok()
                             .body("Product delete " + productDTORequest + " is successful");
    }

    @GetMapping("/read-product")
    public ResponseEntity<ProductDTO> readProduct(@Valid @RequestBody ProductDTO productDTORequest) {
        return ResponseEntity
                .ok()
                .body(productService.readProduct(productDTORequest));
    }

    @GetMapping("/get-all-products")
    public ResponseEntity<ListProductDTO> getAllProducts() {
        return ResponseEntity
                .ok()
                .body(productService.getAllProducts());
    }

    @PostMapping("/sava-all-products")
    public ResponseEntity<String> saveAllProducts(@RequestBody ListProductDTO listProductDTO) {
        productService.savaAllProducts(listProductDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("Update create " + " is successful");
    }
}

