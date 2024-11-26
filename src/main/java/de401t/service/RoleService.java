package de401t.service;

import de401t.dto.RoleDTO;
import de401t.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    public List<RoleDTO> getAll() {
        List<RoleDTO> response = new ArrayList<>();
        roleRepository.findAll().forEach(role -> response.add(modelMapper.map(role, RoleDTO.class)));
        return response;
    }
}
