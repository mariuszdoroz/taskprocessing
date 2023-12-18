package com.example.taskprocessing.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

    @Column(name = "input")
    private String input;

    @Column(name = "pattern")
    private String pattern;

    @Column(name = "status")
    private int status;

    @Column(name = "position")
    private int position;

    @Column(name = "typos")
    private int typos;

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
}
