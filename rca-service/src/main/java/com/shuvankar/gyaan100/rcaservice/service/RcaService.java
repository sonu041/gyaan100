package com.shuvankar.gyaan100.rcaservice.service;

import com.shuvankar.gyaan100.rcaservice.dto.RcaRequest;
import com.shuvankar.gyaan100.rcaservice.dto.RcaResponse;
import com.shuvankar.gyaan100.rcaservice.exception.ResourceNotFoundException;
import com.shuvankar.gyaan100.rcaservice.model.Rca;
import com.shuvankar.gyaan100.rcaservice.repository.RcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.shuvankar.gyaan100.rcaservice.config.AppConstants.ID;


@Service
@RequiredArgsConstructor
public class RcaService {
    private final RcaRepository rcaRepository;

    /** Add rca service */
    public RcaResponse addRca(RcaRequest rcaRequest) {
        Rca rca = new Rca();
        rca.setTitle(rcaRequest.getTitle());
        rca.setDetails(rcaRequest.getDetails());
        rca.setRoot_cause(rcaRequest.getRoot_cause());
        rca.setTimeline(rcaRequest.getTimeline());
        rca.setAction_item(rcaRequest.getAction_item());
        rca.setCreated_date(String.valueOf(LocalDate.now()));
        rcaRepository.save(rca);
        return mapToRcaResponse(rca);
    }

    /** Get All Rca Service */
    public List<RcaResponse> getAllRcas() {
        List<Rca> rcas = rcaRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return rcas.stream().map(this::mapToRcaResponse).toList();
    }

    /** Update Rca Service */
    public void updateRca(Long id, RcaRequest rcaRequest) throws ResourceNotFoundException {
        findById(id).map(p -> {
            BeanUtils.copyProperties(rcaRequest, p);
            return rcaRepository.save(p);
        }).orElseThrow(() -> new ResourceNotFoundException("Rca", ID, id));
    }

    /** Hard Delete Rca */
    public void deleteRcaByID(Long id) {
        rcaRepository.deleteById(id);
    }

    /** Find Rca by id */
    public Optional<Rca> findById(Long id) {
        return rcaRepository.findById(id);
    }

    /** Mapper method to map Rca object with Response */
    private RcaResponse mapToRcaResponse(Rca rca) {
        return RcaResponse.builder()
                .id(rca.getId())
                .title(rca.getTitle())
                .details(rca.getDetails())
                .root_cause(rca.getRoot_cause())
                .timeline(rca.getTimeline())
                .action_item(rca.getAction_item())
                .created_date(rca.getCreated_date())
                .build();
    }
}