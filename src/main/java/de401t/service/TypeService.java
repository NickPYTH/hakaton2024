package de401t.service;

import de401t.dto.TypeDTO;
import de401t.exception.CustomException;
import de401t.model.Type;
import de401t.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypeService {

    private final TypeRepository typeRepository;

    private final ModelMapper modelMapper;

    public List<TypeDTO> getTypes() {
        List<Type> types = typeRepository.findAll();
        return types.stream().map(Type -> modelMapper.map(Type, TypeDTO.class)).collect(Collectors.toList());
    }

    public TypeDTO getTypeById(Long id) {
        return modelMapper.map(typeRepository.findById(id), TypeDTO.class);
    }

    public String create(TypeDTO typeDTO) {
        Type type = modelMapper.map(typeDTO, Type.class);
        typeRepository.save(type);
        throw new CustomException("Type created", HttpStatus.OK);
    }

    public String update(TypeDTO typeDTO) {
        Type type = modelMapper.map(typeDTO, Type.class);
        typeRepository.save(type);
        throw new CustomException("Type updated", HttpStatus.OK);
    }

    public String delete(Long id) {
        typeRepository.deleteById(id);
        throw new CustomException("Type deleted", HttpStatus.OK);
    }

}
