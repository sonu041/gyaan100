package com.shuvankar.gyaan100.serverservice.service;

import com.shuvankar.gyaan100.serverservice.dto.ServerRequest;
import com.shuvankar.gyaan100.serverservice.model.Server;
import com.shuvankar.gyaan100.serverservice.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServerService {
    private final ServerRepository ServerRepository;
    public void addServer(ServerRequest ServerRequest) {
        Server server = new Server();
        server.setName(ServerRequest.getName());
        server.setHostname(ServerRequest.getHostname());
        server.setIp(ServerRequest.getIp());
        server.setType(ServerRequest.getType());
        server.setOs(ServerRequest.getOs());
        server.setStatus(ServerRequest.getStatus());

        ServerRepository.save(server);
    }
}
