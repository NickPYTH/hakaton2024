package de401t.controller;

import de401t.dto.StatusDTO;
import de401t.service.StatusService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/statuses")
@Api(tags = "statuses")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client') or hasAuthority('executor') or hasAuthority('assistant')")    @ApiOperation(value = "${StatusController.getStatuses}", response = StatusDTO.class,
            responseContainer = "List",
            authorizations = {@Authorization(value = "apiKey")})
    public List<StatusDTO> getStatuses() {
        return statusService.getStatuses();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client') or hasAuthority('executor') or hasAuthority('assistant')")    @ApiOperation(value = "${StatusController.getStatusById}", response = StatusDTO.class,
            authorizations = {@Authorization(value = "apiKey")})
    public StatusDTO getStatusById(@ApiParam("Id") @PathVariable Long id) {
        return statusService.getStatusById(id);
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client') or hasAuthority('executor') or hasAuthority('assistant')")    @ApiOperation(value = "${StatusController.create}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ ограничен")})
    public String create(@ApiParam("Status") @RequestBody StatusDTO StatusDTO) {
        return statusService.create(StatusDTO);
    }

    @PutMapping(value = "/update")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client') or hasAuthority('executor') or hasAuthority('assistant')")    @ApiOperation(value = "${StatusController.update}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ ограничен")})
    public String update(@ApiParam("Status") @RequestBody StatusDTO StatusDTO) {
        return statusService.update(StatusDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('client') or hasAuthority('executor') or hasAuthority('assistant')")    @ApiOperation(value = "${StatusController.delete}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ ограничен")})
    public String delete(@ApiParam("Id") @PathVariable Long id) {
        return statusService.delete(id);
    }

}
