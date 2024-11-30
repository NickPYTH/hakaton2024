package de401t.controller;

import de401t.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reports")
@Api(tags = "reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping(value = "/requests")
    @ApiOperation(value = "${ReportController.getReportOnRequests}", response = ResponseEntity.class, authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity getReportOnRequests() {
        return reportService.getReportOnRequests();
    }

}
