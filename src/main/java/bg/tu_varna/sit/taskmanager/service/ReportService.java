package bg.tu_varna.sit.taskmanager.service;

import bg.tu_varna.sit.taskmanager.dto.ReportDto;
import bg.tu_varna.sit.taskmanager.model.Task;

import java.util.List;

public interface ReportService {
    ReportDto addReport(Long taskId, ReportDto reportDto);
    List<ReportDto> getReportsByTask(Long taskId);

    ReportDto getReportById(Long taskId, Long reportId);

    public ReportDto updateReport(Long taskId, Long reportId, ReportDto reportDto);

    void deleteReport(Long taskId, Long reportId);

    /*List<ReportDto> getReportsWithDurationBetween(Long taskId, double min, double max);

    ReportDto getMaxLongedReports(Long taskId);

    Double getTaskWorkingHours(Long taskId);*/
}
