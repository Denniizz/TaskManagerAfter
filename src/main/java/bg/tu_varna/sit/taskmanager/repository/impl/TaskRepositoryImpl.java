package bg.tu_varna.sit.taskmanager.repository.impl;

import bg.tu_varna.sit.taskmanager.model.Task;
import bg.tu_varna.sit.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
    public Task getTaskById(Long id) {
        if(!taskList.isEmpty()) {
            for(Task t: taskList) {
                if(t.getId().equals(id)) {
                    return t;
                }
            }
        }
        return null;
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task updatedTask = getTaskById(id);
        if(updatedTask!=null) {
            updatedTask.setTitle(task.getTitle());
            updatedTask.setDescription(task.getDescription());
            updatedTask.setDeadline(task.getDeadline());
        }
        return updatedTask;
    }

    @Override
    public void deleteTask(Long id) {
        Task deletedTask = getTaskById(id);
        if(deletedTask!=null) {
            taskList.remove(deletedTask);
        }
    }

    @Override
    public List<Task> getAllTasks() {
        return taskList;
    }
}
