package com.softserve.betterlearningroom.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Assignment extends Material {

    private LocalDateTime startDate;

    private LocalDateTime dueDate;

    private List<Criterion> criterions;

    private List<User> students;

    private int maxScore;

    public Assignment(Long id, MaterialType materialType, String title, String text, List<Link> urls, LocalDateTime startDate, LocalDateTime dueDate, List<Criterion> criterions, List<User> students, int maxScore) {
        super(id, materialType, title, text, urls);
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.criterions = criterions;
        this.students = students;
        this.maxScore = maxScore;
    }

    public Assignment() {}

    @Override
    public LocalDateTime getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    @Override
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public List<Criterion> getCriterions() {
        return criterions;
    }

    @Override
    public void setCriterions(List<Criterion> criterions) {
        this.criterions = criterions;
    }

    @Override
    public List<User> getStudents() {
        return students;
    }

    @Override
    public void setStudents(List<User> students) {
        this.students = students;
    }

    @Override
    public int getMaxScore() {
        return maxScore;
    }

    @Override
    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return getMaxScore() == that.getMaxScore() &&
                Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getDueDate(), that.getDueDate()) &&
                Objects.equals(getCriterions(), that.getCriterions()) &&
                getStudents().equals(that.getStudents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartDate(), getDueDate(), getCriterions(), getStudents(), getMaxScore());
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "startDate=" + startDate +
                ", dueDate=" + dueDate +
                ", criterions=" + criterions +
                ", students=" + students +
                ", maxScore=" + maxScore +
                '}';
    }
}
