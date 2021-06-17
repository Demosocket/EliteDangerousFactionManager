package com.demosocket.manager.service.impl;

import com.demosocket.manager.dto.TaskDto;
import com.demosocket.manager.model.Task;
import com.demosocket.manager.repository.TaskRepository;
import com.demosocket.manager.repository.UserRepository;
import com.demosocket.manager.service.TaskService;
import javax.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository , ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskDto findLast() {
        Task task = taskRepository.findFirstByOrderByIdDesc().orElseThrow(EntityNotFoundException::new);
        TaskDto taskDto = modelMapper.map(task, TaskDto.class);
        taskDto.setUserName(task.getUser().getUsername());

        return taskDto;
    }

    @Override
    public TaskDto findById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        TaskDto taskDto = modelMapper.map(task, TaskDto.class);
        taskDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

        return taskDto;
    }

    @Override
    public void editTask(TaskDto taskDto) {
        Task task = modelMapper.map(taskDto, Task.class);
        task.setUser(userRepository.findByUsername(taskDto.getUserName()));

        taskRepository.save(task);
    }
}
