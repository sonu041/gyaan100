package com.shuvankar.gyaan100.rcaservice.controller;

import com.shuvankar.gyaan100.rcaservice.dto.RcaRequest;
import com.shuvankar.gyaan100.rcaservice.dto.RcaResponse;
import com.shuvankar.gyaan100.rcaservice.exception.ResourceNotFoundException;
import com.shuvankar.gyaan100.rcaservice.model.Rca;
import com.shuvankar.gyaan100.rcaservice.service.RcaService;
import com.shuvankar.gyaan100.rcaservice.util.HttpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.shuvankar.gyaan100.rcaservice.config.AppConstants.ID;


@RestController
@RequestMapping("/api/rca")
//@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RcaController {
    private final RcaService rcaService;

    /** Create Rca */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RcaResponse> addRca(@RequestBody RcaRequest rcaRequest) {
        RcaResponse response = new RcaResponse();
        response = rcaService.addRca(rcaRequest);
        return new ResponseEntity<RcaResponse>(response, HttpUtil.getDefaultResponseHeader(), HttpStatus.CREATED);
    }

    /** Get single rca */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rca getRca(@PathVariable Long id ) throws ResourceNotFoundException {
//        return rcaService.getRca();
        return rcaService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rca", ID, id));
    }

    /** Get all rca */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RcaResponse> getAllRcas() {
        return rcaService.getAllRcas();
    }

    /** Get count of server */
    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public int getKnowledgeCount() throws ResourceNotFoundException {
        return rcaService.getAllRcas().size();
    }

    /** Update Rca */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRca(@PathVariable Long id, @RequestBody RcaRequest rcaRequest) throws ResourceNotFoundException {
        rcaService.updateRca(id, rcaRequest);
    }

    /** Delete Rca */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRca(@PathVariable Long id) throws ResourceNotFoundException {
        rcaService.findById(id).map(p -> {
            rcaService.deleteRcaByID(id);
            return true;
        }).orElseThrow(() -> new ResourceNotFoundException("Product", ID, id));
    }
}
