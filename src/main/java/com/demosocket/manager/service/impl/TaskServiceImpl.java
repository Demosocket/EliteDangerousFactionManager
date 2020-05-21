package com.demosocket.manager.service.impl;

import com.demosocket.manager.model.Task;
import com.demosocket.manager.repository.TaskRepository;
import com.demosocket.manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task findLast() {
        return taskRepository.findFirstByOrderByIdDesc();
    }
}
