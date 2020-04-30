package com.ecommerce.service;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.exception.RecordNotFoundException;
import com.ecommerce.model.SaleOrder;
import com.ecommerce.model.ShoppingCart;
import com.ecommerce.model.UserSession;
import com.ecommerce.repository.SaleOrderRepository;
import com.ecommerce.repository.ShoppingCartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SaleOrderService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private SaleOrderRepository saleOrderRepository;

    public Object checkout(SaleOrder saleOrder) {
        ShoppingCart shoppingCart = shoppingCartRepository.getShoppingCart(saleOrder.getUserId());
        if (null == shoppingCart) {
            throw new RecordNotFoundException(404, "User Not found");
        }
        return saleOrderRepository.createSaleOrder(shoppingCart, saleOrder);
    }
}
