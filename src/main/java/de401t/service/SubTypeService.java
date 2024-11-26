package de401t.service;

import de401t.dto.SubTypeDTO;
import de401t.exception.CustomException;
import de401t.model.SubType;
import de401t.repository.SubTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubTypeService {

    private final SubTypeRepository subTypeRepository;

    private final ModelMapper modelMapper;

    public List<SubTypeDTO> getSubTypes() {
        List<SubType> subTypes = subTypeRepository.findAll();
        return subTypes.stream().map(subType -> modelMapper.map(subType, SubTypeDTO.class)).collect(Collectors.toList());
    }

    public SubTypeDTO getSubTypeById(Long id) {
        return modelMapper.map(subTypeRepository.findById(id), SubTypeDTO.class);
    }

    public String create(SubTypeDTO subTypeDTO) {
        SubType subType = modelMapper.map(subTypeDTO, SubType.class);
        subTypeRepository.save(subType);
        throw new CustomException("SubType created", HttpStatus.OK);
    }

    public String update(SubTypeDTO subTypeDTO) {
        SubType subType = modelMapper.map(subTypeDTO, SubType.class);
        subTypeRepository.save(subType);
        throw new CustomException("SubType updated", HttpStatus.OK);
    }

    public String delete(Long id) {
        subTypeRepository.deleteById(id);
        throw new CustomException("SubType deleted", HttpStatus.OK);
    }

}
