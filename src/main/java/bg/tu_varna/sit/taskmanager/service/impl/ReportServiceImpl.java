package bg.tu_varna.sit.taskmanager.service.impl;

import bg.tu_varna.sit.taskmanager.dto.ReportDto;
import bg.tu_varna.sit.taskmanager.exception.ResourceNotFoundException;
import bg.tu_varna.sit.taskmanager.exception.TaskApiException;
import bg.tu_varna.sit.taskmanager.model.Report;
import bg.tu_varna.sit.taskmanager.model.Task;
import bg.tu_varna.sit.taskmanager.repository.ReportRepository;
import bg.tu_varna.sit.taskmanager.repository.TaskRepository;
import bg.tu_varna.sit.taskmanager.service.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    private TaskRepository taskRepository;
    private ReportRepository reportRepository;
    private ModelMapper mapper;

    public ReportServiceImpl(TaskRepository taskRepository, ReportRepository reportRepository, ModelMapper mapper) {
        this.taskRepository = taskRepository;
        this.reportRepository = reportRepository;
        this.mapper = mapper;
    }

    @Override
    public ReportDto addReport(Long taskId, ReportDto reportDto) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId.toString()));
        Report report = mapToEntity(reportDto);
        report.setTask(task);
        Report addedReport = reportRepository.save(report);
        return mapToDto(addedReport);
    }

    @Override
    public List<ReportDto> getReportsByTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId.toString()));
        List<Report> reports = reportRepository.findAllByTask(task);
        return reports.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ReportDto getReportById(Long taskId, Long reportId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId.toString()));
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Report", "id", reportId.toString()));
        if (!task.getId().equals(report.getTask().getId())) {
            throw new TaskApiException("The report does not belong to the task");
        }
        return mapToDto(report);
    }

    @Override
    public ReportDto updateReport(Long taskId, Long reportId, ReportDto reportDto) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId.toString()));
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Report", "id", reportId.toString()));

        if (!task.getId().equals(report.getTask().getId())) {
            throw new TaskApiException("The report does not belong to the task");
        }

        Report newDataReport = mapToEntity(reportDto);
        report.setContent(newDataReport.getContent());
        report.setHoursWorked(newDataReport.getHoursWorked());

        Report updatedReport = reportRepository.save(report);
        return mapToDto(updatedReport);
    }

    @Override
    public void deleteReport(Long taskId, Long reportId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId.toString()));
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Report", "id", reportId.toString()));

        if (!task.getId().equals(report.getTask().getId())) {
            throw new TaskApiException("The report does not belong to the task");
        }
        reportRepository.delete(report);
    }


  /*  @Override
    public List<ReportDto> getReportsWithDurationBetween(Long taskId, double min, double max) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId.toString()));
        List<Report> reports = reportRepository.findByTaskAndHoursWorkedBetween(task,min,max);
        return reports.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ReportDto getMaxLongedReports(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId.toString()));
        Report report = reportRepository.findLongestReport(taskId);
        return mapToDto(report);
    }

    @Override
    public Double getTaskWorkingHours(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId.toString()));
        double hours = reportRepository.getAllReportsLength(taskId);
        return hours;
    }

*/

    private Report mapToEntity(ReportDto reportDto) {
        return mapper.map(reportDto, Report.class);
    }

    private ReportDto mapToDto(Report report) {
        return mapper.map(report, ReportDto.class);
    }
}
