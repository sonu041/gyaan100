package com.shuvankar.gyaan100.contactservice.util;

import org.springframework.http.HttpHeaders;

public class HttpUtil {
    public static HttpHeaders getDefaultResponseHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=utf-8");
        return headers;
    }
}
