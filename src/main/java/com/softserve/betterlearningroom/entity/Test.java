package com.softserve.betterlearningroom.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Test extends Assignment {

    private String url;

    public Test() {
    }

    public Test(Long id, MaterialType materialType, String title, String text, List<Link> urls, LocalDateTime startDate, LocalDateTime dueDate, List<Criterion> criterions, List<User> students, int maxScore, String url) {
        super(id, materialType, title, text, urls, startDate, dueDate, criterions, students, maxScore);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Test test = (Test) o;
        return Objects.equals(getUrl(), test.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUrl());
    }

    @Override
    public String toString() {
        return "Test{" +
                "url='" + url + '\'' +
                '}';
    }
}