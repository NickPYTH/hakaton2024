package de401t.service;

import de401t.dto.PriorityDTO;
import de401t.exception.CustomException;
import de401t.model.Priority;
import de401t.repository.PriorityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriorityService {

    private final PriorityRepository priorityRepository;

    private final ModelMapper modelMapper;

    public List<PriorityDTO> getPriorities() {
        List<Priority> prioritys = priorityRepository.findAll();
        return prioritys.stream().map(priority -> modelMapper.map(priority, PriorityDTO.class)).collect(Collectors.toList());
    }

    public PriorityDTO getPriorityById(Long id) {
        return modelMapper.map(priorityRepository.findById(id), PriorityDTO.class);
    }

    public String create(PriorityDTO priorityDTO) {
        Priority priority = modelMapper.map(priorityDTO, Priority.class);
        priorityRepository.save(priority);
        throw new CustomException("Priority created", HttpStatus.OK);
    }

    public String update(PriorityDTO priorityDTO) {
        Priority priority = modelMapper.map(priorityDTO, Priority.class);
        priorityRepository.save(priority);
        throw new CustomException("Priority updated", HttpStatus.OK);
    }

    public String delete(Long id) {
        priorityRepository.deleteById(id);
        throw new CustomException("Priority deleted", HttpStatus.OK);
    }

}
