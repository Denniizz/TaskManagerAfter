package bg.tu_varna.sit.taskmanager.repository.impl;

import bg.tu_varna.sit.taskmanager.exception.ResourceNotFoundException;
import bg.tu_varna.sit.taskmanager.model.Task;
import bg.tu_varna.sit.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    private static TaskRepositoryImpl instance;
    private List<Task> taskList;

    private static Long id;

    private TaskRepositoryImpl() {
        taskList = new ArrayList<>();
        id = 1L;
    }

    public static TaskRepositoryImpl getInstance() {
        if(instance==null) {
            instance = new TaskRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Task addTask(Task task) {
        task.setId(id++);
        taskList.add(task);
        return task;
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        Optional<Task> task = Optional.empty();
        if(!taskList.isEmpty()) {
            for(Task t: taskList) {
                if(t.getId().equals(id)) {
                    task = Optional.of(t);
                }
            }
        }
        return task;
    }

    @Override
    public Task updateTask(Task task) {
        //Логиката тук се дублира, т.к. обновяването на запис в БД и в
        // List колекция се извършва на различен принцип.
        // Сървис класът е подготвен за включване на БД следващия час
        Task updatedTask = getTaskById(task.getId())
                .orElseThrow(() -> new ResourceNotFoundException("task", "id", id.toString()));
            updatedTask.setTitle(task.getTitle());
            updatedTask.setDescription(task.getDescription());
            updatedTask.setDeadline(task.getDeadline());
        return updatedTask;
    }

    @Override
    public void deleteTask(Task task) {
        taskList.remove(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskList;
    }
}
