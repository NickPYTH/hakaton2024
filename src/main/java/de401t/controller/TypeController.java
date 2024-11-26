package de401t.controller;

import de401t.dto.TypeDTO;
import de401t.service.TypeService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/types")
@Api(tags = "types")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "${TypeController.getTypes}", response = TypeDTO.class,
            responseContainer = "List",
            authorizations = {@Authorization(value = "apiKey")})
    public List<TypeDTO> getTypes() {
        return typeService.getTypes();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "${TypeController.getTypeById}", response = TypeDTO.class,
            authorizations = {@Authorization(value = "apiKey")})
    public TypeDTO getTypeById(@ApiParam("Id") @PathVariable Long id) {
        return typeService.getTypeById(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "${TypeController.create}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ огрнаничен")})
    public String create(@ApiParam("Type") @RequestBody TypeDTO TypeDTO) {
        return typeService.create(TypeDTO);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "${TypeController.update}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ огрнаничен")})
    public String update(@ApiParam("Type") @RequestBody TypeDTO TypeDTO) {
        return typeService.update(TypeDTO);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "${TypeController.delete}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ огрнаничен")})
    public String delete(@ApiParam("Id") @PathVariable Long id) {
        return typeService.delete(id);
    }

}
