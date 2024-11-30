package de401t.service;

import de401t.dto.RequestDTO;
import de401t.exception.CustomException;
import de401t.model.Request;
import de401t.model.Role;
import de401t.model.User;
import de401t.repository.RequestRepository;
import de401t.repository.StatusRepository;
import de401t.repository.UserRepository;
import de401t.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final StatusRepository statusRepository;
    private final ModelMapper modelMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final DefaultEmailService emailService;
    private final TelegramService telegramService;

    public RequestDTO getRequestById(Long id) {
        return modelMapper.map(requestRepository.findById(id), RequestDTO.class);
    }

    public String create(String tgId, RequestDTO requestDTO) {
        if(tgId != null && !tgId.isEmpty()) {
            if(userRepository.findByTgId(tgId) == null) {
                throw new CustomException("Tg user not found", HttpStatus.BAD_REQUEST);
            }
        }
        Request request = modelMapper.map(requestDTO, Request.class);
        request.setCreateDate(new Date());
        request.setStatus(statusRepository.getById(1L));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(request.getCreateDate());
        calendar.add(Calendar.HOUR_OF_DAY, request.getPriority().getTerm().intValue());
        request.setDeadlineDate(calendar.getTime());
        request = requestRepository.save(request);
        if(request.getGroup() != null) {
            List<User> userList = userRepository.findAllByGroup(request.getGroup());
            emailService.sendEmailForGroup(userList, "Создана заявка", "На вашу группу была создана заявка ID " +
                            request.getId() + " пользователем " + request.getClient().getEmail() + ".");
            telegramService.sendNotifyForGroup(userList, request);
        }
        throw new CustomException("Request created", HttpStatus.OK);
    }

    public String update(RequestDTO requestDTO) {
        Request request = modelMapper.map(requestDTO, Request.class);
        request.setUpdateDate(new Date());
        requestRepository.save(request);
        if(request.getStatus() != null && request.getStatus().getId().equals(3L)) {
            emailService.sendSimpleEmail(request.getClient().getEmail(), "Заявка выполнена", "Ваша заявка ID " + request.getId() + " была выполнена.");
        }
        throw new CustomException("Request updated", HttpStatus.OK);
    }

    public String delete(Long id) {
        requestRepository.deleteById(id);
        throw new CustomException("Request deleted", HttpStatus.OK);
    }

    public List<RequestDTO> getRequestsByRole(HttpServletRequest req) {
        User user = userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        Role role = user.getRoles().get(0);
        if (role.getAuthority().equals("client")){
            List<Request> requests = requestRepository.findAllByClientOrderById(user);
            return requests.stream().map(request -> modelMapper.map(request, RequestDTO.class)).collect(Collectors.toList());
        } else if (role.getAuthority().equals("executor")) {
            List<Request> requests = requestRepository.findAllByExecutorOrderById(user);
            requests.addAll(requestRepository.findAllByStatusId(1L));
            return requests.stream().map(request -> modelMapper.map(request, RequestDTO.class)).collect(Collectors.toList());
        }
        else if (role.getAuthority().equals("admin")) {
            List<Request> requests = requestRepository.findAllByOrderById();
            return requests.stream().map(request -> modelMapper.map(request, RequestDTO.class)).collect(Collectors.toList());
        } else {
            throw new CustomException("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    public List<RequestDTO> getRequests() {
        List<Request> requests = requestRepository.findAllByOrderById();
        return requests.stream().map(request -> modelMapper.map(request, RequestDTO.class)).collect(Collectors.toList());
    }

}
