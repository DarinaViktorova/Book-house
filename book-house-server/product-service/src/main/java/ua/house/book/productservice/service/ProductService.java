package ua.house.book.productservice.service;

import ua.house.book.productservice.domain.dto.request.ListProductDTO;
import ua.house.book.productservice.domain.dto.request.ProductDTO;

public interface ProductService {

    void createProduct(ProductDTO productDTORequest);

    void updateProduct(ProductDTO productDTORequest);

    void deleteProduct(ProductDTO productDTORequest);

    ProductDTO readProduct(ProductDTO productDTORequest);

    ListProductDTO getAllProducts();

    void savaAllProducts(ListProductDTO listProductDTO);
}
