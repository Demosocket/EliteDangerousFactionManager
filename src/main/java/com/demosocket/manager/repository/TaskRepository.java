package com.demosocket.manager.repository;

import com.demosocket.manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findFirstByOrderByIdDesc();
}
