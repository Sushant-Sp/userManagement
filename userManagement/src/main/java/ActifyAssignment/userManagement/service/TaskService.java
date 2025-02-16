package ActifyAssignment.userManagement.service;

import ActifyAssignment.userManagement.dto.TaskDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TaskService {

    void assignTask(TaskDto taskDto);
    List<TaskDto> getAllTasks();
}
