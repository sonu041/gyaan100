package com.shuvankar.gyaan100.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApplication.class, args);

    }
    //TODO: Enable basic security to access Discovery service via API Gateway.
    // Currently it can be accessed using direct link. e.g. http://localhost:8761/
    // It should be accessed using http://localhost:8080/eureka/web
}
