package de401t.service;

import de401t.dto.EquipmentDTO;
import de401t.exception.CustomException;
import de401t.model.Equipment;
import de401t.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    private final ModelMapper modelMapper;

    public List<EquipmentDTO> getEquipments() {
        List<Equipment> Equipments = equipmentRepository.findAll();
        return Equipments.stream().map(Equipment -> modelMapper.map(Equipment, EquipmentDTO.class)).collect(Collectors.toList());
    }

    public EquipmentDTO getEquipmentById(Long id) {
        return modelMapper.map(equipmentRepository.findById(id), EquipmentDTO.class);
    }

    public String create(EquipmentDTO EquipmentDTO) {
        Equipment Equipment = modelMapper.map(EquipmentDTO, Equipment.class);
        equipmentRepository.save(Equipment);
        throw new CustomException("Equipment created", HttpStatus.OK);
    }

    public String update(EquipmentDTO EquipmentDTO) {
        Equipment Equipment = modelMapper.map(EquipmentDTO, Equipment.class);
        equipmentRepository.save(Equipment);
        throw new CustomException("Equipment updated", HttpStatus.OK);
    }

    public String delete(Long id) {
        equipmentRepository.deleteById(id);
        throw new CustomException("Equipment deleted", HttpStatus.OK);
    }

}
