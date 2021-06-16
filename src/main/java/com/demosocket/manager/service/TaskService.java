package com.demosocket.manager.service;

import com.demosocket.manager.dto.TaskDto;

public interface TaskService {

    TaskDto findLast();

    TaskDto findById(Long id);

    void editTask(TaskDto taskDto);
}
