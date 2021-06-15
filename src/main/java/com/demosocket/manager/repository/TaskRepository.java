package com.demosocket.manager.repository;

import com.demosocket.manager.model.Task;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findFirstByOrderByIdDesc();
}
