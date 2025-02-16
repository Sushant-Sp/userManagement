package ActifyAssignment.userManagement.service.Impl;

import ActifyAssignment.userManagement.dto.TaskDto;
import ActifyAssignment.userManagement.entity.TaskEntity;
import ActifyAssignment.userManagement.entity.UserEntity;
import ActifyAssignment.userManagement.repository.TaskRepository;
import ActifyAssignment.userManagement.repository.UserRepository;
import ActifyAssignment.userManagement.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void assignTask(TaskDto taskDto) {
        log.info("Received TaskDto: {}", taskDto);
        // Retrieve the UserEntity using userId from TaskDto
        UserEntity assignedUser = userRepository.findById(taskDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        log.info("Fetched assignedUser: {}", assignedUser);

        TaskEntity task = new TaskEntity();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        UserEntity user = userRepository.findById(taskDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setAssignedUser(user);

        taskRepository.save(task);

        log.info("Task saved successfully!");
    }
    @Override
    public List<TaskDto> getAllTasks() {
        List<TaskEntity> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> new TaskDto())
                .collect(Collectors.toList());
    }
}

