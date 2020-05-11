package com.ecommerce.service;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
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

    @Autowired
    private ProductService productService;

    public ShoppingCart addToCart(String sessionId, CartItem item) throws BusinessException {
        // validate session first
        UserSession session = userSessionService.validateSession(sessionId);
        if (null == session) {
            throw new BusinessException(404, "User Not found");
        }
        // validate product
        Product product = productService.findProductById(item.getProductId());
        if (null == product) {
            throw new BusinessException(404, "Product Not found");
        }
        item.setPrice(product.getPrice());
        return shoppingCartRepository.addToCart(session.getUserId(), item);
    }

    public String updateProductQty(String sessionId, CartItem item, String action) throws BusinessException {
        UserSession session = userSessionService.validateSession(sessionId);
        if (null == session) {
            throw new BusinessException(404, "User Not found");
        }
        ShoppingCart userCart = getShoppingCart(session.getUserId());
        if (null == userCart) {
            throw new BusinessException(404, "Shopping cart not found");
        }

        item.setCartId(userCart.getId());
        shoppingCartRepository.updateProductQty(item, action);
        return "Success";
    }

    public ShoppingCart getShoppingCart(String userId) {
        return shoppingCartRepository.getShoppingCart(userId);
    }

    public void deleteProductInCart(String itemId) {
        shoppingCartRepository.deleteProductInCart(itemId);
    }
}
