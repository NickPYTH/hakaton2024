package de401t.service;

import de401t.dto.RequestDTO;
import de401t.exception.CustomException;
import de401t.model.Request;
import de401t.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;

    private final ModelMapper modelMapper;

    public List<RequestDTO> getRequests() {
        List<Request> requests = requestRepository.findAll();
        return requests.stream().map(request -> modelMapper.map(request, RequestDTO.class)).collect(Collectors.toList());
    }

    public RequestDTO getRequestById(Long id) {
        return modelMapper.map(requestRepository.findById(id), RequestDTO.class);
    }

    public String create(RequestDTO requestDTO) {
        Request request = modelMapper.map(requestDTO, Request.class);
        requestRepository.save(request);
        throw new CustomException("Request created", HttpStatus.OK);
    }

    public String update(RequestDTO requestDTO) {
        Request request = modelMapper.map(requestDTO, Request.class);
        requestRepository.save(request);
        throw new CustomException("Request updated", HttpStatus.OK);
    }

    public String delete(Long id) {
        requestRepository.deleteById(id);
        throw new CustomException("Request deleted", HttpStatus.OK);
    }

}
