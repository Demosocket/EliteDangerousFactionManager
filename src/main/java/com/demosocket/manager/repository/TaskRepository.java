package com.demosocket.manager.repository;

import com.demosocket.manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findFirstByOrderByIdDesc();
}
