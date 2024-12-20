package de401t.service;

import de401t.dto.RoleDTO;
import de401t.dto.UserDTO;
import de401t.exception.CustomException;
import de401t.model.Group;
import de401t.model.Role;
import de401t.model.User;
import de401t.repository.GroupRepository;
import de401t.repository.RoleRepository;
import de401t.repository.UserRepository;
import de401t.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;

    public HashMap<String, String> login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            HashMap<String, String> response = new HashMap<>();
            User user = userRepository.findByUsername(username);
            response.put("token", jwtTokenProvider.createToken(username, user.getRoles()));
            response.put("role", user.getRoles().get(0).getCode().toString());

            return response;
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String register(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<UserDTO, User> propertyMapper = modelMapper.createTypeMap(UserDTO.class, User.class);
        propertyMapper.addMappings(mapper -> mapper.skip(User::setRoles));
        User user = modelMapper.map(userDTO, User.class);
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Group group = groupRepository.getById(userDTO.getGroup().getId());
            user.setGroup(group);
            user.setIsBoss(userDTO.getIsBoss());
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findById(userDTO.getRole().getId()));
            user.setRoles(roles);
            userRepository.save(user);
            throw new CustomException("Created", HttpStatus.CREATED);
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String update(UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findById(userDTO.getId());
        if (!userOpt.isPresent())
            throw new CustomException("User does not exist", HttpStatus.UNPROCESSABLE_ENTITY);
        User user = userOpt.get();
        Group group = groupRepository.getById(userDTO.getGroup().getId());
        user.setGroup(group);
        user.setIsBoss(userDTO.getIsBoss());
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(userDTO.getRole().getId()));
        user.setRoles(roles);
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setSecondName(userDTO.getSecondName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setTgId(userDTO.getTgId());
        user.setTgName(userDTO.getTgName());
        if (userDTO.getPassword() != null)
            if (userDTO.getPassword().trim().length() > 0)
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        throw new CustomException("Updated", HttpStatus.OK);
    }

    public String delete(String username) {
        userRepository.deleteByUsername(username);
        throw new CustomException("User deleted", HttpStatus.OK);
    }

    public User search(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public UserDTO whoami(HttpServletRequest req) {
        ModelMapper modelMapper = new ModelMapper();
        User user = userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setRole(modelMapper.map(user.getRoles().get(0), RoleDTO.class));
        return userDTO;
    }

    public List<UserDTO> getUsers() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<User, UserDTO> propertyMapper = modelMapper.createTypeMap(User.class, UserDTO.class);
        propertyMapper.addMappings(mapper -> mapper.skip(UserDTO::setPassword));
        List<UserDTO> response = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            RoleDTO roleDTO = modelMapper.map(user.getRoles().get(0), RoleDTO.class);
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTO.setRole(roleDTO);
            response.add(userDTO);
        }
        return response;
    }

    @Transactional
    public UserDTO addTgId(String tgName, String tgId) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<User> userOpt = userRepository.findByTgName(tgName);
        if (userOpt.isPresent()){
            User user = userOpt.get();
            user.setTgId(tgId);
            userRepository.save(user);
            return modelMapper.map(user, UserDTO.class);
        } else throw new CustomException("User with tgName not found", HttpStatus.NOT_FOUND);
    }
}
