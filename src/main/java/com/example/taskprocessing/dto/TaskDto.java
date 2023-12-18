package com.example.taskprocessing.dto;

public class TaskDto {

    private Long id;
    private String input;
    private String pattern;
    private int status;
    private int position;
    private int typos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTypos() {
        return typos;
    }

    public void setTypos(int typos) {
        this.typos = typos;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", input='" + input + '\'' +
                ", pattern='" + pattern + '\'' +
                ", status=" + status +
                ", position=" + position +
                ", typos=" + typos +
                '}';
    }
}
