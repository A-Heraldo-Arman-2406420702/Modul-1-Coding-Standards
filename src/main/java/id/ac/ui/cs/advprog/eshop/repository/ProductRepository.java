package id.ac.ui.cs.advprog.eshop.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

@Repository
public class ProductRepository {

    private final List<Product> productData = new ArrayList<>();

    public Product create(Product product) {

        if (product.getProductId() == null) {
            product.setProductId(UUID.randomUUID().toString());
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String id) {
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Product edit(Product product) {
        for (int i = 0; i < productData.size(); i++) {
            Product p = productData.get(i);
            if (p.getProductId().equals(product.getProductId())) {
                productData.set(i, product);
                return product;
            }
        }
        return null;
    }
}
