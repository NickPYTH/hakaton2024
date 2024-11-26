package de401t.controller;

import de401t.dto.RoleDTO;
import de401t.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/roles")
@Api(tags = "roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/all")
    @ApiOperation(value = "${RoleController.getAll}", response = RoleDTO.class, responseContainer = "List")
    public List<RoleDTO> getAll() {
        return roleService.getAll();
    }

}
