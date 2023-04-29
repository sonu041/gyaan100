package com.shuvankar.gyaan100.serverservice.controller;

import com.shuvankar.gyaan100.serverservice.exception.ResourceNotFoundException;
import com.shuvankar.gyaan100.serverservice.dto.ServerRequest;
import com.shuvankar.gyaan100.serverservice.dto.ServerResponse;
import com.shuvankar.gyaan100.serverservice.model.Server;
import com.shuvankar.gyaan100.serverservice.service.ServerService;
import com.shuvankar.gyaan100.serverservice.util.HttpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.shuvankar.gyaan100.serverservice.config.AppConstants.ID;

@RestController
@RequestMapping("/api/server")
@RequiredArgsConstructor
public class ServerController {
    private final ServerService serverService;

    /** Create Servers */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ServerResponse> addServer(@RequestBody ServerRequest ServerRequest) {
        ServerResponse response = new ServerResponse();
        response = serverService.addServer(ServerRequest);
        return new ResponseEntity<ServerResponse>(response, HttpUtil.getDefaultResponseHeader(), HttpStatus.CREATED);
    }

    /** Get single server */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Server getServer(@PathVariable Long id ) throws ResourceNotFoundException {
        return serverService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", ID, id));
    }

    /** Get all Servers */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ServerResponse> getAllServers() {
        return serverService.getAllServers();
    }

    /** Get count of server */
    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public int getKnowledgeCount() throws ResourceNotFoundException {
        return serverService.getAllServers().size();
    }

    /** Update Servers */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateServer(@PathVariable Long id, @RequestBody ServerRequest serverRequest) throws ResourceNotFoundException {
        serverService.updateServer(id, serverRequest);
    }

    /** Delete Server */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteServer(@PathVariable Long id) throws ResourceNotFoundException {
        serverService.findById(id).map(p -> {
            serverService.deleteServerByID(id);
            return true;
        }).orElseThrow(() -> new ResourceNotFoundException("Product", ID, id));
    }
}
