package bg.tu_varna.sit.taskmanager.service;

import bg.tu_varna.sit.taskmanager.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TaskService {
    Task addTask(Task task);
    Task getTaskById(Long id);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
    List<Task> getAllTasks();
}
