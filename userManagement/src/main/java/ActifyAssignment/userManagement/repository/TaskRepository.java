package ActifyAssignment.userManagement.repository;

import ActifyAssignment.userManagement.entity.TaskEntity;
import ActifyAssignment.userManagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByAssignedUser(UserEntity user);
}
