package bg.tu_varna.sit.taskmanager.controller;

import bg.tu_varna.sit.taskmanager.dto.ReportDto;
import bg.tu_varna.sit.taskmanager.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportController {
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    //api/tasks/1/reports
    @PostMapping("api/tasks/{taskId}/reports")
    public ResponseEntity<ReportDto> addReport(@PathVariable("taskId") Long taskId,
                                               @RequestBody ReportDto reportDto) {
        ReportDto reportDto1 = reportService.addReport(taskId, reportDto);
        return new ResponseEntity<>(reportDto1, HttpStatus.CREATED);
    }

    @GetMapping("api/tasks/{taskId}/reports")
    public ResponseEntity<List<ReportDto>> getReportsByTask(@PathVariable("taskId") Long taskId) {
        List<ReportDto> reportDtos = reportService.getReportsByTask(taskId);
        return new ResponseEntity<>(reportDtos, HttpStatus.OK);
    }

    @GetMapping("api/tasks/{taskId}/reports/{reportId}")
    public ResponseEntity<ReportDto> getReportById(@PathVariable("taskId") Long taskId,
                                                   @PathVariable("reportId") Long reportId) {
        ReportDto reportDto = reportService.getReportById(taskId, reportId);
        return new ResponseEntity<>(reportDto, HttpStatus.OK);
    }

    @PutMapping("api/tasks/{taskId}/reports/{reportId}/edit")
    public ResponseEntity<ReportDto> updateReport(@PathVariable("taskId") Long taskId,
                                                  @PathVariable("reportId") Long reportId,
                                                  @RequestBody ReportDto reportDto) {
        ReportDto editedReportDto = reportService.updateReport(taskId, reportId, reportDto);
        return new ResponseEntity<>(editedReportDto, HttpStatus.OK);
    }

    @DeleteMapping("api/tasks/{taskId}/reports/{reportId}")
    public ResponseEntity<String> deleteReport(@PathVariable("taskId") Long taskId,
                                               @PathVariable("reportId") Long reportId) {
        reportService.deleteReport(taskId, reportId);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }
/*
    @GetMapping("api/tasks/{taskId}/reports/max")
    public ResponseEntity<ReportDto> getMaxReport(@PathVariable("taskId") Long id) {
        ReportDto report = reportService.getMaxLongedReports(id);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping("api/tasks/{task_id}/reports/hours")
    public ResponseEntity<Double> getTaskHours(@PathVariable("task_id") Long id) {
        Double hours = reportService.getTaskWorkingHours(id);
        return new ResponseEntity<>(hours, HttpStatus.OK);
    }

    @GetMapping("api/tasks/{taskId}/reports/between")
    public ResponseEntity<List<ReportDto>> getReportsBetweenDurations(
            @PathVariable("taskId") Long id,
            @RequestParam(name="min") double min,
            @RequestParam(name="max") double max) {
        List<ReportDto> reports = reportService.getReportsWithDurationBetween(id, min, max);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }
    */

}
