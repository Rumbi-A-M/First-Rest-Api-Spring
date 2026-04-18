package pl.edu.vistula.firstrestapispring.product.repository;

import pl.edu.vistula.firstrestapispring.product.domain.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {

    private final Map<Long, Product> database = new HashMap<>();
    private Long index = 1L;

    public Product save(Product product) {
        product.setId(index);
        database.put(index, product);
        index++;
        return product;
    }
}