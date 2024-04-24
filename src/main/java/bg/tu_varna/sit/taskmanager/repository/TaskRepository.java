package bg.tu_varna.sit.taskmanager.repository;

import bg.tu_varna.sit.taskmanager.dto.TaskDto;
import bg.tu_varna.sit.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task, Long> {

}
