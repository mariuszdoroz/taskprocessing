package com.example.taskprocessing.service;

import com.example.taskprocessing.dto.TaskDto;
import com.example.taskprocessing.mapper.TaskMapper;
import com.example.taskprocessing.model.Task;
import com.example.taskprocessing.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    final TaskRepository taskRepository;
    final TaskProcessor taskProcessor;
    final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskProcessor taskProcessor, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskProcessor = taskProcessor;
        this.taskMapper = taskMapper;
    }

    public TaskDto create(TaskDto taskDto) {
        Task task = taskMapper.mapToEntity(taskDto);
        Task savedTask = taskRepository.create(task);
        taskDto.setId(savedTask.getId());
        return taskDto;
    }

    public void update(TaskDto taskDto) {
        Task task = taskMapper.mapToEntity(taskDto);
        Task savedTask = taskRepository.update(task);
        taskDto.setId(savedTask.getId());
    }

    public List<TaskDto> getAll() {
        return taskMapper.mapToDtoList(taskRepository.getAll());
    }

    public TaskDto getById(Long taskId) {
        return taskMapper.mapToDto(taskRepository.getById(taskId));
    }

    @Async
    public void taskProcess(TaskDto taskDto) {
        try {
            logger.info("processTask id: {} - start", taskDto.getId());
            Random random = new Random();
            int sleepTime = random.nextInt(1000,11000);
            Thread.sleep(sleepTime);
            taskProcessor.checkFirstBestMatch(taskDto);
            update(taskDto);
            logger.info("processTask id: {} - end with success", taskDto.getId());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}