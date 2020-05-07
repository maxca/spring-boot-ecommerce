package com.ecommerce.controller;

import com.ecommerce.BaseTest;
import com.ecommerce.model.User;
import com.ecommerce.service.SaleOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SaleOrderControllerTest extends BaseTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaleOrderService saleOrderService;

    private String baseUrl = "/v1";

    @Test
    public void postCheckoutSuccess() throws Exception {
        Object saleOrderRequest = this.getResourceRequest("checkout/CheckoutSuccess");
        doReturn(new User())
                .when(saleOrderService)
                .checkout(any());
        this.tesCurlPostControllerFromHeader(baseUrl.concat("/sale-order"), saleOrderRequest, "200");
    }

    @Test
    public void postCheckoutValidateFail() throws Exception {
        Object saleOrderRequest = this.getResourceRequest("checkout/CheckoutValidateFail");
        doReturn(new User())
                .when(saleOrderService)
                .checkout(any());
        this.tesCurlPostController(baseUrl.concat("/sale-order"), saleOrderRequest, "400");
    }
}
