package com.ecommerce.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class UserLoginRequest {
    @NotNull(message = "The email file is required")
    private String email;
    @NotNull(message = "The password field is required")
    private String password;
}
