package com.example.ecommerce.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonConstants {
    /**
     * General
     */
    public static final String LONG_FORMAT_DATETIME = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public static final String SQL_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIMEZONE_BANGKOK = "Asia/Bangkok";
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final String LANGUAGE_TH = "TH";
    public static final String LANGUAGE_EN = "EN";
    public static final String REGEX_NUMERIC_ONLY = "[0-9]+";
    public static final String DEFAULT_TIME_ZONE = "Asia/Bangkok";
}
