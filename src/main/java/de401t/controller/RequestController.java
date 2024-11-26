package de401t.controller;

import de401t.dto.RequestDTO;
import de401t.service.RequestService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/requests")
@Api(tags = "requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "${RequestController.getRequests}", response = RequestDTO.class,
            responseContainer = "List",
            authorizations = {@Authorization(value = "apiKey")})
    public List<RequestDTO> getRequests() {
        return requestService.getRequests();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "${RequestController.getRequestById}", response = RequestDTO.class,
            authorizations = {@Authorization(value = "apiKey")})
    public RequestDTO getRequestById(@ApiParam("Id") @PathVariable Long id) {
        return requestService.getRequestById(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "${RequestController.create}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ огрнаничен")})
    public String create(@ApiParam("Request") @RequestBody RequestDTO requestDTO) {
        return requestService.create(requestDTO);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "${RequestController.update}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ огрнаничен")})
    public String update(@ApiParam("Request") @RequestBody RequestDTO requestDTO) {
        return requestService.update(requestDTO);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "${RequestController.delete}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Что-то пошло не так"),
            @ApiResponse(code = 403, message = "Доступ огрнаничен")})
    public String delete(@ApiParam("Id") @PathVariable Long id) {
        return requestService.delete(id);
    }

}
