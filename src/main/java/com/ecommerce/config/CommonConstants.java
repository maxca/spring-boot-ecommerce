package com.ecommerce.config;

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


    /**
     * Status Code
     */
    public static final int RESPONSE_CODE_1000 = 1000;    //Success / สำเร็จ
    public static final int RESPONSE_CODE_1001 = 1001;    //Record is not found
    public static final int RESPONSE_CODE_1899 = 1899;    //Information not available / ระบบไม่สามารถทำรายการได้ในขณะนี้  Note This is a catch-all status code for all business-related errors that are not mapped (e.g. validation error, data not found, data API returned business error, etc)
    public static final int RESPONSE_CODE_1999 = 1999;    //Information not available / ระบบไม่สามารถทำรายการได้ในขณะนี้   Note This is a catch-all status code for all system-related errors
    public static final int RESPONSE_CODE_9000 = 9000;    //Timeout in response
}
