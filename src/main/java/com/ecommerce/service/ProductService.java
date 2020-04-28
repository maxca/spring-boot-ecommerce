package com.ecommerce.service;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserSessionService userSessionService;

    public Product findProductById(String sessionId, String productId) throws BusinessException {
        // validate session first
        userSessionService.validateSession(sessionId);
        Product product = productRepository.findProductById(productId);
        if (null == product) {
            throw new BusinessException(404, "Not found product");
        }
        return product;
    }
}
