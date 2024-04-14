package bg.tu_varna.sit.taskmanager.service;

import bg.tu_varna.sit.taskmanager.dto.TaskDto;
import bg.tu_varna.sit.taskmanager.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TaskService {
    TaskDto addTask(TaskDto taskDto);
    TaskDto getTaskById(Long id);
    TaskDto updateTask(Long id, TaskDto task);
    void deleteTask(Long id);
    List<TaskDto> getAllTasks();
}
