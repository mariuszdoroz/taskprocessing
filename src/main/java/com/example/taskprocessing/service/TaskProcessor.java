package com.example.taskprocessing.service;

import com.example.taskprocessing.dto.TaskDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TaskProcessor {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    public void checkFirstBestMatch(TaskDto taskDto) {
        String input = taskDto.getInput();
        String pattern = taskDto.getPattern();
        int bestIndexPosition = input.indexOf(pattern);
        int minTypos = 0;
        if (bestIndexPosition == -1) {
            int inputLength = taskDto.getInput().length();
            int patternLength = taskDto.getPattern().length();
            int typos;
            minTypos = patternLength;

            for (int i = 0; i <= inputLength - patternLength; i++) {
                String substring = input.substring(i, i + patternLength);
                typos = countTypos(substring, pattern);
                if (typos < minTypos || (typos == minTypos && i < bestIndexPosition)) {
                    bestIndexPosition = i;
                    minTypos = typos;
                }
            }
        }
        taskDto.setPosition(bestIndexPosition);
        taskDto.setTypos(minTypos);
        taskDto.setStatus(100);
    }

    private int countTypos(String str1, String str2) {
        int typos = 0;
        int minLength = Math.min(str1.length(), str2.length());
        for (int i = 0; i < minLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                typos++;
            }
        }
        typos += Math.abs(str1.length() - str2.length());
        return typos;
    }
}
