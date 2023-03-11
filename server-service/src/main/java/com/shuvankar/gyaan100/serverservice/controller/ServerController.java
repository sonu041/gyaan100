package com.shuvankar.gyaan100.serverservice.controller;

import com.shuvankar.gyaan100.serverservice.dto.ServerRequest;
import com.shuvankar.gyaan100.serverservice.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/server")
@RequiredArgsConstructor
public class ServerController {
    private final ServerService serverService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addServer(@RequestBody ServerRequest ServerRequest) {
        serverService.addServer(ServerRequest);
        return "Server Added Successfully";
    }
}
