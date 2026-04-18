package pl.edu.vistula.firstrestapispring.product.support;

import pl.edu.vistula.firstrestapispring.product.api.request.ProductRequest;
import pl.edu.vistula.firstrestapispring.product.api.response.ProductResponse;
import pl.edu.vistula.firstrestapispring.product.domain.Product;

public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        return product;
    }

    public ProductResponse toProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        return response;
    }
}