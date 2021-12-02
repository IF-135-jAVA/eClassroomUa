package com.softserve.betterlearningroom.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Task extends Assignment {

    private String task;

    private String answer;

    public Task(Long id, MaterialType materialType, String title, String text, List<Link> urls, LocalDateTime startDate, LocalDateTime dueDate, List<Criterion> criterions, List<User> students, int maxScore, String task, String answer) {
        super(id, materialType, title, text, urls, startDate, dueDate, criterions, students, maxScore);
        this.task = task;
        this.answer = answer;
    }

    public Task() {}

    @Override
    public String getTask() {
        return task;
    }

    @Override
    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Task task1 = (Task) o;
        return Objects.equals(getTask(), task1.getTask()) &&
                Objects.equals(getAnswer(), task1.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTask(), getAnswer());
    }

    @Override
    public String toString() {
        return "Task{" +
                "task='" + task + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}