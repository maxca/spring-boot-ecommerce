package com.ecommerce.service;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.ShoppingCart;
import com.ecommerce.model.UserSession;
import com.ecommerce.repository.ShoppingCartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserSessionService userSessionService;

    public ShoppingCart addToCart(String sessionId, CartItem item) throws BusinessException {
        // validate session first
        UserSession session = userSessionService.validateSession(sessionId);
        if (null == session) {
            throw new BusinessException(404, "User Not found");
        }
        return shoppingCartRepository.addToCart(session.getUserId(), item);
    }

    public ShoppingCart getShoppingCart(String sessionId) {
        UserSession session = userSessionService.validateSession(sessionId);
        if (null == session) {
            throw new BusinessException(404, "User Not found");
        }
        return shoppingCartRepository.getShoppingCart(session.getUserId());
    }
}
