package com.ecommerce.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class UserLoginRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
