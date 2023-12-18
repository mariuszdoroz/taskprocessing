package com.example.taskprocessing.mapper;

import com.example.taskprocessing.dto.TaskDto;
import com.example.taskprocessing.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    public TaskDto mapToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setInput(task.getInput());
        taskDto.setPattern(task.getPattern());
        taskDto.setStatus(task.getStatus());
        taskDto.setPosition(task.getPosition());
        taskDto.setTypos(task.getTypos());
        return taskDto;
    }

    public Task mapToEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setInput(taskDto.getInput());
        task.setPattern(taskDto.getPattern());
        task.setStatus(taskDto.getStatus());
        task.setPosition(taskDto.getPosition());
        task.setTypos(taskDto.getTypos());
        return task;
    }

    public List<TaskDto> mapToDtoList(List<Task> tasks) {
        List<TaskDto> taskDtoList = new ArrayList<>();
        for (Task task : tasks) {
            TaskDto taskDto = mapToDto(task);
            taskDtoList.add(taskDto);
        }
        return taskDtoList;
    }
}
