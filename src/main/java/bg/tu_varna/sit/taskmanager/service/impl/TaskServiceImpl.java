package bg.tu_varna.sit.taskmanager.service.impl;

import bg.tu_varna.sit.taskmanager.dto.TaskDto;
import bg.tu_varna.sit.taskmanager.exception.ResourceNotFoundException;
import bg.tu_varna.sit.taskmanager.model.Task;
import bg.tu_varna.sit.taskmanager.repository.TaskRepository;
import bg.tu_varna.sit.taskmanager.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private ModelMapper mapper;

    public TaskServiceImpl(ModelMapper mapper, TaskRepository taskRepository) {
        this.mapper = mapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDto addTask(TaskDto taskDto) {
        Task task = mapToEntity(taskDto);
        Task updatedTask = taskRepository.save(task);
        return mapToDto(updatedTask);
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("task", "id", id.toString()));
        return mapToDto(task);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("task", "id", id.toString()));
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDeadline(taskDto.getDeadline());
        taskRepository.save(task);
        return mapToDto(task);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("task", "id", id.toString()));
        taskRepository.delete(task);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private Task mapToEntity(TaskDto taskDto) {
        return mapper.map(taskDto, Task.class);
    }

    private TaskDto mapToDto(Task task) {
        return mapper.map(task, TaskDto.class);
    }

}
