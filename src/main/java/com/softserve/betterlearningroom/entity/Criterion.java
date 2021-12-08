package com.softserve.betterlearningroom.entity;

import java.util.List;
import java.util.Objects;

public class Criterion {

    private Long id;

    private String title;

    private String description;

    private List<Level> levels;

    public Criterion() { }

    public Criterion(Long id, String title, String description, List<Level> levels) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.levels = levels;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Criterion criterion = (Criterion) o;
        return getId().equals(criterion.getId()) &&
                getTitle().equals(criterion.getTitle()) &&
                Objects.equals(getDescription(), criterion.getDescription()) &&
                Objects.equals(getLevels(), criterion.getLevels());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getLevels());
    }

    @Override
    public String toString() {
        return "Criterion{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", levels=" + levels +
                '}';
    }
}
