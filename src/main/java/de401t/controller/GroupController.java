package de401t.controller;

import de401t.dto.GroupDTO;
import de401t.service.GroupService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/groups")
@Api(tags = "groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client')")
    @ApiOperation(value = "${GroupController.getGroups}", response = GroupDTO.class,
            responseContainer = "List",
            authorizations = {@Authorization(value = "apiKey")})
    public List<GroupDTO> getGroups() {
        return groupService.getGroups();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client')")
    @ApiOperation(value = "${GroupController.getGroupById}", response = GroupDTO.class,
            authorizations = {@Authorization(value = "apiKey")})
    public GroupDTO getGroupById(@ApiParam("Id") @PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client')")
    @ApiOperation(value = "${GroupController.create}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ ограничен")})
    public String create(@ApiParam("Group") @RequestBody GroupDTO GroupDTO) {
        return groupService.create(GroupDTO);
    }

    @PutMapping(value = "/update")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client')")
    @ApiOperation(value = "${GroupController.update}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ ограничен")})
    public String update(@ApiParam("Group") @RequestBody GroupDTO GroupDTO) {
        return groupService.update(GroupDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client')")
    @ApiOperation(value = "${GroupController.delete}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ ограничен")})
    public String delete(@ApiParam("Id") @PathVariable Long id) {
        return groupService.delete(id);
    }

}
