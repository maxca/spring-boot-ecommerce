package com.ecommerce.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class SessionIdRequest {

    @NotNull
    @Size(min = 10, max = 30)
    private String sessionId;
}
