package com.softserve.betterlearningroom.model;

import java.util.Objects;

public class Level {

    private Long id;

    private String title;

    private String description;

    private Integer mark;

    public Level(Long id, String title, String description, Integer mark) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.mark = mark;
    }

    public Level() {
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

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Level level = (Level) o;
        return getId().equals(level.getId()) &&
                getTitle().equals(level.getTitle()) &&
                Objects.equals(getDescription(), level.getDescription()) &&
                getMark().equals(level.getMark());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getMark());
    }

    @Override
    public String toString() {
        return "Level{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", mark=" + mark +
                '}';
    }
}
