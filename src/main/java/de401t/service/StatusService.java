package de401t.service;

import de401t.dto.StatusDTO;
import de401t.exception.CustomException;
import de401t.model.Status;
import de401t.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository statusRepository;

    private final ModelMapper modelMapper;

    public List<StatusDTO> getStatuses() {
        List<Status> statuses = statusRepository.findAll();
        return statuses.stream().map(status -> modelMapper.map(status, StatusDTO.class)).collect(Collectors.toList());
    }

    public StatusDTO getStatusById(Long id) {
        return modelMapper.map(statusRepository.findById(id), StatusDTO.class);
    }

    public String create(StatusDTO statusDTO) {
        Status status = modelMapper.map(statusDTO, Status.class);
        statusRepository.save(status);
        throw new CustomException("Status created", HttpStatus.OK);
    }

    public String update(StatusDTO statusDTO) {
        Status status = modelMapper.map(statusDTO, Status.class);
        statusRepository.save(status);
        throw new CustomException("Status updated", HttpStatus.OK);
    }

    public String delete(Long id) {
        statusRepository.deleteById(id);
        throw new CustomException("Status deleted", HttpStatus.OK);
    }

}
