package de401t.service;

import de401t.dto.GroupDTO;
import de401t.exception.CustomException;
import de401t.model.Group;
import de401t.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    private final ModelMapper modelMapper;

    public List<GroupDTO> getGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups.stream().map(Group -> modelMapper.map(Group, GroupDTO.class)).collect(Collectors.toList());
    }

    public GroupDTO getGroupById(Long id) {
        return modelMapper.map(groupRepository.findById(id), GroupDTO.class);
    }

    public String create(GroupDTO groupDTO) {
        Group group = modelMapper.map(groupDTO, Group.class);
        groupRepository.save(group);
        throw new CustomException("Group created", HttpStatus.OK);
    }

    public String update(GroupDTO groupDTO) {
        Group group = modelMapper.map(groupDTO, Group.class);
        groupRepository.save(group);
        throw new CustomException("Group updated", HttpStatus.OK);
    }

    public String delete(Long id) {
        groupRepository.deleteById(id);
        throw new CustomException("Group deleted", HttpStatus.OK);
    }

}
