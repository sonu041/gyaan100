package com.shuvankar.gyaan100.serverservice.service;

import com.shuvankar.gyaan100.serverservice.exception.ResourceNotFoundException;
import com.shuvankar.gyaan100.serverservice.dto.ServerRequest;
import com.shuvankar.gyaan100.serverservice.dto.ServerResponse;
import com.shuvankar.gyaan100.serverservice.model.Server;
import com.shuvankar.gyaan100.serverservice.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.shuvankar.gyaan100.serverservice.config.AppConstants.ID;

@Service
@RequiredArgsConstructor
public class ServerService {
    private final ServerRepository serverRepository;

    /** Add server service */
    public ServerResponse addServer(ServerRequest ServerRequest) {
        Server server = new Server();
        server.setName(ServerRequest.getName());
        server.setHostname(ServerRequest.getHostname());
        server.setIp(ServerRequest.getIp());
        server.setType(ServerRequest.getType());
        server.setOs(ServerRequest.getOs());
        server.setStatus(ServerRequest.getStatus());
        serverRepository.save(server);
        return mapToServerResponse(server);
    }


    /** Mapper method to map Server object with Response */
    private ServerResponse mapToServerResponse(Server server) {
        return ServerResponse.builder()
                .id(server.getId())
                .name(server.getName())
                .hostname(server.getHostname())
                .ip(server.getIp())
                .type(server.getType())
                .os(server.getOs())
                .status(server.getStatus())
                .build();
    }

    /** Get All Server Service */
    public List<ServerResponse> getAllServers() {
        List<Server> knowledges = serverRepository.findAll();
        return knowledges.stream().map(this::mapToServerResponse).toList();
    }

    /** Update Server Service */
    public void updateServer(Long id, ServerRequest serverRequest) throws ResourceNotFoundException {
        findById(id).map(p -> {
            BeanUtils.copyProperties(serverRequest, p);
            return serverRepository.save(p);
        }).orElseThrow(() -> new ResourceNotFoundException("Server", ID, id));
    }

    /** Hard Delete Server */
    public void deleteServerByID(Long id) {
        serverRepository.deleteById(id);
    }

    /** Find Knowledge by id */
    public Optional<Server> findById(Long id) {
        return serverRepository.findById(id);
    }
}
