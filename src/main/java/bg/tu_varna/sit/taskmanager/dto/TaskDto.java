package bg.tu_varna.sit.taskmanager.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDto {
    private Long id;
    @NotEmpty(message="Title must not be empty")
    private String title;
    @Size(min=10, message="Description must be more than 10 symbols")
    private String description;
    @NotNull(message="Deadline must not be missing")
    private String deadline;
}