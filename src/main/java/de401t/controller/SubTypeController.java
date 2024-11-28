package de401t.controller;

import de401t.dto.SubTypeDTO;
import de401t.service.SubTypeService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/subtypes")
@Api(tags = "subtypes")
@RequiredArgsConstructor
public class SubTypeController {

    private final SubTypeService subTypeService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client')")
    @ApiOperation(value = "${SubTypeController.getSubTypes}", response = SubTypeDTO.class,
            responseContainer = "List",
            authorizations = {@Authorization(value = "apiKey")})
    public List<SubTypeDTO> getSubTypes() {
        return subTypeService.getSubTypes();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client')")
    @ApiOperation(value = "${SubTypeController.getSubTypeById}", response = SubTypeDTO.class,
            authorizations = {@Authorization(value = "apiKey")})
    public SubTypeDTO getSubTypeById(@ApiParam("Id") @PathVariable Long id) {
        return subTypeService.getSubTypeById(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client')")
    @ApiOperation(value = "${SubTypeController.create}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ огрнаничен")})
    public String create(@ApiParam("SubType") @RequestBody SubTypeDTO SubTypeDTO) {
        return subTypeService.create(SubTypeDTO);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client')")
    @ApiOperation(value = "${SubTypeController.update}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ огрнаничен")})
    public String update(@ApiParam("SubType") @RequestBody SubTypeDTO SubTypeDTO) {
        return subTypeService.update(SubTypeDTO);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client')")
    @ApiOperation(value = "${SubTypeController.delete}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ огрнаничен")})
    public String delete(@ApiParam("Id") @PathVariable Long id) {
        return subTypeService.delete(id);
    }

}
