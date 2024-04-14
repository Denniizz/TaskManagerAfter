package bg.tu_varna.sit.taskmanager.repository;

import bg.tu_varna.sit.taskmanager.dto.TaskDto;
import bg.tu_varna.sit.taskmanager.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface TaskRepository {
    Task addTask(Task task);
    Optional<Task> getTaskById(Long id);
    Task updateTask(Task task);
    void deleteTask(Task task);
    List<Task> getAllTasks();
}
