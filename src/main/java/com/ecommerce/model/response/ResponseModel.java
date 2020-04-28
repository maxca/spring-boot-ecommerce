package com.ecommerce.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ResponseModel {
    private String statusCode;
    private String statusHeader;
    private List<?> data;

    public ResponseModel(String statusCode) {
        this.statusCode = statusCode;
    }

    public ResponseModel(String statusHeader, List<?> data) {
        this.statusHeader = statusHeader;
        this.data = data;
    }

    public ResponseModel(String statusCode, String statusHeader) {
        this.statusCode = statusCode;
        this.statusHeader = statusHeader;
    }

    public ResponseModel(String statusCode, String statusHeader, List<?> data) {
        this.statusCode = statusCode;
        this.statusHeader = statusHeader;
        this.data = data;
    }
}

