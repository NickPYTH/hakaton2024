package de401t.controller;

import de401t.dto.EquipmentDTO;
import de401t.service.EquipmentService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/equipments")
@Api(tags = "equipments")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client') or hasAuthority('executor') or hasAuthority('assistant')")    @ApiOperation(value = "${GroupController.getGroups}", response = EquipmentDTO.class,
            responseContainer = "List",
            authorizations = {@Authorization(value = "apiKey")})
    public List<EquipmentDTO> getGroups() {
        return equipmentService.getEquipments();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client') or hasAuthority('executor') or hasAuthority('assistant')")    @ApiOperation(value = "${GroupController.getGroupById}", response = EquipmentDTO.class,
            authorizations = {@Authorization(value = "apiKey")})
    public EquipmentDTO getGroupById(@ApiParam("Id") @PathVariable Long id) {
        return equipmentService.getEquipmentById(id);
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client') or hasAuthority('executor') or hasAuthority('assistant')")    @ApiOperation(value = "${GroupController.create}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ ограничен")})
    public String create(@ApiParam("Group") @RequestBody EquipmentDTO EquipmentDTO) {
        return equipmentService.create(EquipmentDTO);
    }

    @PutMapping(value = "/update")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client') or hasAuthority('executor') or hasAuthority('assistant')")    @ApiOperation(value = "${GroupController.update}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ ограничен")})
    public String update(@ApiParam("Group") @RequestBody EquipmentDTO EquipmentDTO) {
        return equipmentService.update(EquipmentDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client') or hasAuthority('executor') or hasAuthority('assistant')")    @ApiOperation(value = "${GroupController.delete}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ ограничен")})
    public String delete(@ApiParam("Id") @PathVariable Long id) {
        return equipmentService.delete(id);
    }

}
