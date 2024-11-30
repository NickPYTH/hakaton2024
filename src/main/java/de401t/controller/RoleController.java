package de401t.controller;

import de401t.dto.RoleDTO;
import de401t.dto.UserDTO;
import de401t.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client') or hasAuthority('executor') or hasAuthority('assistant')")    @ApiOperation(value = "${RoleController.getAll}", response = RoleDTO.class,
            responseContainer = "List",
            authorizations = {@Authorization(value = "apiKey")})
    public List<RoleDTO> getAll() {
        return roleService.getAll();
    }

}
