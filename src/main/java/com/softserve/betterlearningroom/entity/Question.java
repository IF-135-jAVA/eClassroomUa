package com.softserve.betterlearningroom.entity;

import java.util.Objects;

public class Question {

    private Long id;

    private String question;

    public Question(Long id, String question) {
        this.id = id;
        this.question = question;
    }

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return getId().equals(question1.getId()) &&
                Objects.equals(getQuestion(), question1.getQuestion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getQuestion());
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                '}';
    }
}