package de401t.service;

import de401t.dto.RequestDTO;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final RequestService requestService;

    public ResponseEntity getReportOnRequests(HttpServletRequest req) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Заявки");
            int rowNum = 0;
            Row headerRow = sheet.createRow(rowNum++);
            Cell cell = headerRow.createCell(0);
            cell.setCellValue("id");
            cell = headerRow.createCell(1);
            cell.setCellValue("Название");
            cell = headerRow.createCell(2);
            cell.setCellValue("Описание");
            cell = headerRow.createCell(3);
            cell.setCellValue("Статус");
            cell = headerRow.createCell(4);
            cell.setCellValue("Приоритет");
            cell = headerRow.createCell(5);
            cell.setCellValue("Тип заявки");
            cell = headerRow.createCell(6);
            cell.setCellValue("Заявитель");
            cell = headerRow.createCell(7);
            cell.setCellValue("Исполнитель");
            cell = headerRow.createCell(8);
            cell.setCellValue("Решение");
            cell = headerRow.createCell(9);
            cell.setCellValue("Примечание");
            cell = headerRow.createCell(10);
            cell.setCellValue("Дата создания");
            cell = headerRow.createCell(11);
            cell.setCellValue("Дедлайн");
            List<RequestDTO> requestList = requestService.getRequests(req);
            for (RequestDTO request : requestList) {
                Row dataRow = sheet.createRow(rowNum++);
                cell = dataRow.createCell(0);
                cell.setCellValue(request.getId());
                cell = dataRow.createCell(1);
                cell.setCellValue(request.getName());
                cell = dataRow.createCell(2);
                cell.setCellValue(request.getDescription());
                cell = dataRow.createCell(3);
                cell.setCellValue(request.getStatus() != null ? request.getStatus().getName() : "");
                cell = dataRow.createCell(4);
                cell.setCellValue(request.getPriority() != null ? request.getPriority().getName() : "");
                cell = dataRow.createCell(5);
                cell.setCellValue(request.getType() != null && request.getSubType() != null ? request.getType() + " (" + request.getSubType().getName() + ")" : "");
                cell = dataRow.createCell(6);
                cell.setCellValue(request.getClient() != null ? request.getClient().getName() : "");
                cell = dataRow.createCell(7);
                cell.setCellValue(request.getExecutor() != null ? request.getExecutor().getName() : "");
                cell = dataRow.createCell(8);
                cell.setCellValue(request.getSolution());
                cell = dataRow.createCell(9);
                cell.setCellValue(request.getComment());
                cell = dataRow.createCell(10);
                cell.setCellValue(request.getCreateDate().toString());
                cell = dataRow.createCell(11);
                cell.setCellValue(request.getDeadlineDate().toString());
            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                workbook.write(bos);
            } finally {
                bos.close();
            }
            byte[] fileContent = bos.toByteArray();
            return ResponseEntity.ok()
                    .contentType(new MediaType("application", "xlsx"))
                    .contentLength(fileContent.length)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"export.xlsx\"")
                    .body(fileContent);
        } catch (NoResultException | IOException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header(HttpHeaders.CONTENT_TYPE, "text/html; charset=utf-8")
                    .body("Ошибка получения файла подтверждения (файл не найден)");
        }
    }

}
