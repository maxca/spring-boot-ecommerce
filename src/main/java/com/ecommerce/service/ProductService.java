package com.ecommerce.service;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.model.Product;
import com.ecommerce.model.UserSession;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserSessionService userSessionService;

    public Product findProductById(String productId) throws BusinessException {
        // validate session first
//        userSessionService.validateSession(sessionId);
        Product product = productRepository.findProductById(productId);
        if (null == product) {
            throw new BusinessException(404, "Not found product");
        }
        return product;
    }

    public List<Product> getAllProduct() throws BusinessException {
        return productRepository.getAllProduct();
    }
}
