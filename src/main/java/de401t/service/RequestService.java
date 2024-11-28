package de401t.service;

import de401t.dto.RequestDTO;
import de401t.exception.CustomException;
import de401t.model.Request;
import de401t.model.Role;
import de401t.model.User;
import de401t.repository.RequestRepository;
import de401t.repository.UserRepository;
import de401t.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final ModelMapper modelMapper;
    private final JwtTokenProvider jwtTokenProvider;

    public RequestDTO getRequestById(Long id) {
        return modelMapper.map(requestRepository.findById(id), RequestDTO.class);
    }

    public String create(RequestDTO requestDTO) {
        Request request = modelMapper.map(requestDTO, Request.class);
        request.setCreateDate(new Date());
        requestRepository.save(request);
        throw new CustomException("Request created", HttpStatus.OK);
    }

    public String update(RequestDTO requestDTO) {
        Request request = modelMapper.map(requestDTO, Request.class);
        request.setUpdateDate(new Date());
        requestRepository.save(request);
        throw new CustomException("Request updated", HttpStatus.OK);
    }

    public String delete(Long id) {
        requestRepository.deleteById(id);
        throw new CustomException("Request deleted", HttpStatus.OK);
    }

    public List<RequestDTO> getRequests(HttpServletRequest req) {
        User user = userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        Role role = user.getRoles().get(0);
        if (role.getAuthority().equals("client")){
            List<Request> requests = requestRepository.findAllByClientOrderById(user);
            return requests.stream().map(request -> modelMapper.map(request, RequestDTO.class)).collect(Collectors.toList());
        } else if (role.getAuthority().equals("executor")) {
            List<Request> requests = requestRepository.findAllByExecutorOrderById(user);
            return requests.stream().map(request -> modelMapper.map(request, RequestDTO.class)).collect(Collectors.toList());
        }
        else if (role.getAuthority().equals("admin")) {
            List<Request> requests = requestRepository.findAllByOrderById();
            return requests.stream().map(request -> modelMapper.map(request, RequestDTO.class)).collect(Collectors.toList());
        } else {
            throw new CustomException("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }
}
