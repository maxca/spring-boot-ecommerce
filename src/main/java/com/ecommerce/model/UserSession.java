package com.ecommerce.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Accessors(chain = true)
public class UserSession {
    private String id;
    private String userId;
    @DateTimeFormat
    private String expiredTime;
}
