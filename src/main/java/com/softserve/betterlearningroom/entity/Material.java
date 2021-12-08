package com.softserve.betterlearningroom.entity;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Material {

    private Long id;

    private MaterialType materialType;

    private String title;

    private String text;

    private List<Link> urls;

    public Material(Long id, MaterialType materialType, String title, String text, List<Link> urls) {
        this.id = id;
        this.materialType = materialType;
        this.title = title;
        this.text = text;
        this.urls = urls;
    }

    public Material() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Link> getUrls() {
        return urls;
    }

    public void setUrls(List<Link> urls) {
        this.urls = urls;
    }

    public LocalDateTime getStartDate() {
        return null;
    }

    public void setStartDate(LocalDateTime startDate) { }

    public LocalDateTime getDueDate() {
        return null;
    }

    public void setDueDate(LocalDateTime dueDate) { }

    public List<Criterion> getCriterions() {
        return null;
    }

    public void setCriterions(List<Criterion> criterions) { }

    public List<User> getStudents() {
        return null;
    }

    public void setStudents(List<User> students) { }

    public int getMaxScore() {
        return 0;
    }

    public void setMaxScore(int maxScore) { }

    public String getTask() {
        return null;
    }

    public void setTask(String task) { }

    public String getAnswer() {
        return null;
    }

    public void setAnswer(String answer) { }

    public String getUrl() {
        return null;
    }

    public void setUrl(String url) { }

    public List<Question> getQuestions() {
        return null;
    }

    public void setQuestions(List<Question> questions) { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return getId().equals(material.getId()) &&
                getMaterialType() == material.getMaterialType() &&
                getTitle().equals(material.getTitle()) &&
                Objects.equals(getText(), material.getText()) &&
                Objects.equals(getUrls(), material.getUrls());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMaterialType(), getTitle(), getText(), getUrls());
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", materialType=" + materialType +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", urls=" + urls +
                '}';
    }
}
