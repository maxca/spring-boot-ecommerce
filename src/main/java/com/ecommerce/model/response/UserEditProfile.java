package com.ecommerce.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UserEditProfile implements Serializable {
    @NotNull
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String name;
}
