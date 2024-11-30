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
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client') or hasAuthority('executor')")
    @ApiOperation(value = "${TypeController.getTypes}", response = TypeDTO.class,
            responseContainer = "List",
            authorizations = {@Authorization(value = "apiKey")})
    public List<TypeDTO> getTypes() {
        return typeService.getTypes();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client') or hasAuthority('executor')")
    @ApiOperation(value = "${TypeController.getTypeById}", response = TypeDTO.class,
            authorizations = {@Authorization(value = "apiKey")})
    public TypeDTO getTypeById(@ApiParam("Id") @PathVariable Long id) {
        return typeService.getTypeById(id);
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client') or hasAuthority('executor')")
    @ApiOperation(value = "${TypeController.create}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ ограничен")})
    public String create(@ApiParam("Type") @RequestBody TypeDTO TypeDTO) {
        return typeService.create(TypeDTO);
    }

    @PutMapping(value = "/update")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client') or hasAuthority('executor')")
    @ApiOperation(value = "${TypeController.update}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ ограничен")})
    public String update(@ApiParam("Type") @RequestBody TypeDTO TypeDTO) {
        return typeService.update(TypeDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client') or hasAuthority('executor')")
    @ApiOperation(value = "${TypeController.delete}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ ограничен")})
    public String delete(@ApiParam("Id") @PathVariable Long id) {
        return typeService.delete(id);
    }

}
