package bg.tu_varna.sit.taskmanager.repository;

import bg.tu_varna.sit.taskmanager.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TaskRepository {
    Task addTask(Task task);
    Task getTaskById(Long id);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
    List<Task> getAllTasks();
}
