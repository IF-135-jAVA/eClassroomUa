package com.softserve.betterlearningroom.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Questions extends Assignment {

    private List<Question> questions;

    public Questions() {
    }

    public Questions(Long id, MaterialType materialType, String title, String text, List<Link> urls, LocalDateTime startDate, LocalDateTime dueDate, List<Criterion> criterions, List<User> students, int maxScore, List<Question> questions) {
        super(id, materialType, title, text, urls, startDate, dueDate, criterions, students, maxScore);
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Questions questions1 = (Questions) o;
        return Objects.equals(getQuestions(), questions1.getQuestions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getQuestions());
    }

    @Override
    public String toString() {
        return "Questions{" +
                "questions=" + questions +
                '}';
    }
}
