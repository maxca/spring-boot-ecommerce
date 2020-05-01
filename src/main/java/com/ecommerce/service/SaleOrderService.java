package com.ecommerce.service;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.exception.RecordNotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.model.SaleOrder;
import com.ecommerce.model.ShoppingCart;
import com.ecommerce.model.UserSession;
import com.ecommerce.repository.SaleOrderRepository;
import com.ecommerce.repository.ShoppingCartRepository;
import com.ecommerce.repository.UserSessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SaleOrderService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private SaleOrderRepository saleOrderRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    public Object checkout(String sessionId) {
        if (null == sessionId) {
            throw new RecordNotFoundException(404, "SessionId Not found");
        }
        UserSession userSession = userSessionRepository.findUserSessionBySessionId(sessionId);
        if (null == userSession) {
            throw new RecordNotFoundException(404, "User Not found");
        }
        ShoppingCart shoppingCart = shoppingCartRepository.getShoppingCart(userSession.getUserId());
        if (null == shoppingCart) {
            throw new RecordNotFoundException(404, "Item In Cart Not found");
        }
        return saleOrderRepository.createSaleOrder(shoppingCart);
    }

    public List<SaleOrder> getAllSaleOrder() throws BusinessException {
        return saleOrderRepository.getAllSaleOrder();
    }
}
