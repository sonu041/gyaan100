package com.shuvankar.gyaan100.serverservice.util;

import org.springframework.http.HttpHeaders;

public class HttpUtil {
    public static HttpHeaders getDefaultResponseHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=utf-8");
        return headers;
    }
}
