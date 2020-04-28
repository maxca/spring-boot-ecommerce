package com.ecommerce.service;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.model.Product;
import com.ecommerce.model.UserSession;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserSessionService userSessionService;

    public Product createProduct(String sessionId, Product product) throws BusinessException {
        // validate session first
        UserSession session = userSessionService.validateSession(sessionId);
        if (null == session) {
            throw new BusinessException(404, "User Not found");
        }
        product.setId(UUID.randomUUID().toString());
        product.setUserId(session.getUserId());
        return productRepository.createProduct(product);
    }

    public Product findProductById(String productId) throws BusinessException {
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
