package com.example.taskprocessing.controller;

import com.example.taskprocessing.dto.TaskDto;
import com.example.taskprocessing.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/status/{id}")
    public TaskDto getTaskStatus(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @GetMapping("/showAll")
    public List<TaskDto> getAllTasks() {
        return taskService.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        String baseAddress = getBaseAddress();
        taskService.create(taskDto);
        taskService.taskProcess(taskDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(baseAddress.concat("/status/{id}"))
                .buildAndExpand(taskDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(taskDto);
    }

    private String getBaseAddress() {
        RequestMapping requestMapping = getClass().getAnnotation(RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            return requestMapping.value()[0];
        }
        return "";
    }
}