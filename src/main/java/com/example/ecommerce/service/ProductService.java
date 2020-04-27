package com.example.ecommerce.service;

import com.example.ecommerce.exception.BusinessException;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product checkExistingProduct(String productId) throws BusinessException {
        Product product = productRepository.findProductById(productId);
        if (null == product) {
            throw new BusinessException(404, "Not found product");
        }
        return product;
    }
}
