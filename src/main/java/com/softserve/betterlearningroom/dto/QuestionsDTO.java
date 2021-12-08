package com.softserve.betterlearningroom.dto;

import com.softserve.betterlearningroom.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionsDTO extends MaterialDTO{

    private LocalDateTime startDate;

    private LocalDateTime dueDate;

    private List<Criterion> criterions;

    private List<User> students;

    private int maxScore;

    private List<Question> questions;

    public QuestionsDTO(Long id, MaterialType materialType, String title, String text, List<Link> urls, LocalDateTime startDate, LocalDateTime dueDate, List<Criterion> criterions, List<User> students, int maxScore, List<Question> questions) {
        super(id, materialType, title, text, urls);
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.criterions = criterions;
        this.students = students;
        this.maxScore = maxScore;
        this.questions = questions;
    }

    public QuestionsDTO() { }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public List<Criterion> getCriterions() {
        return criterions;
    }

    public void setCriterions(List<Criterion> criterions) {
        this.criterions = criterions;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
