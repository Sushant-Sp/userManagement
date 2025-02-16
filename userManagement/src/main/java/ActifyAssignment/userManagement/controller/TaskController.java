package ActifyAssignment.userManagement.controller;

import ActifyAssignment.userManagement.dto.TaskDto;
import ActifyAssignment.userManagement.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/assign")
    public ResponseEntity<String> assignTask(@RequestBody TaskDto taskDto) {
        if (taskDto.getUserId() == null) {
            return ResponseEntity.badRequest().body("User ID cannot be null");
        }
        taskService.assignTask(taskDto);
        return ResponseEntity.ok("Task assigned successfully!");
    }


    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
}
