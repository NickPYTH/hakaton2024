package de401t.controller;

import de401t.dto.PriorityDTO;
import de401t.service.PriorityService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/priority")
@Api(tags = "priority")
@RequiredArgsConstructor
public class PriorityController {

    private final PriorityService priorityService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client')")
    @ApiOperation(value = "${PriorityController.getPriorities}", response = PriorityDTO.class,
            responseContainer = "List",
            authorizations = {@Authorization(value = "apiKey")})
    public List<PriorityDTO> getPriorities() {
        return priorityService.getPriorities();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client')")
    @ApiOperation(value = "${PriorityController.getPriorityById}", response = PriorityDTO.class,
            authorizations = {@Authorization(value = "apiKey")})
    public PriorityDTO getPriorityById(@ApiParam("Id") @PathVariable Long id) {
        return priorityService.getPriorityById(id);
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client')")
    @ApiOperation(value = "${PriorityController.create}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ огрнаничен")})
    public String create(@ApiParam("Priority") @RequestBody PriorityDTO PriorityDTO) {
        return priorityService.create(PriorityDTO);
    }

    @PutMapping(value = "/update")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client')")
    @ApiOperation(value = "${PriorityController.update}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ огрнаничен")})
    public String update(@ApiParam("Priority") @RequestBody PriorityDTO PriorityDTO) {
        return priorityService.update(PriorityDTO);
    }

    @PostMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client')")
    @ApiOperation(value = "${PriorityController.delete}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ огрнаничен")})
    public String delete(@ApiParam("Id") @PathVariable Long id) {
        return priorityService.delete(id);
    }

}
