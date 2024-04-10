package bg.tu_varna.sit.taskmanager.service.impl;

import bg.tu_varna.sit.taskmanager.model.Task;
import bg.tu_varna.sit.taskmanager.repository.TaskRepository;
import bg.tu_varna.sit.taskmanager.repository.impl.TaskRepositoryImpl;
import bg.tu_varna.sit.taskmanager.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    public TaskServiceImpl() {
        taskRepository = TaskRepositoryImpl.getInstance();
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.addTask(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.getTaskById(id);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        return taskRepository.updateTask(id, task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteTask(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }
}
