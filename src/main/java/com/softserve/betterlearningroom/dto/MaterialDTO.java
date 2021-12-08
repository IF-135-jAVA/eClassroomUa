package com.softserve.betterlearningroom.dto;

import com.softserve.betterlearningroom.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MaterialDTO {

    private Long id;

    private MaterialType materialType;

    private String title;

    private String text;

    private List<Link> urls;

    public MaterialDTO(Long id, MaterialType materialType, String title, String text, List<Link> urls) {
        this.id = id;
        this.materialType = materialType;
        this.title = title;
        this.text = text;
        this.urls = urls;
    }

    public MaterialDTO() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialDTO that = (MaterialDTO) o;
        return getId().equals(that.getId()) &&
                getMaterialType() == that.getMaterialType() &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getText(), that.getText()) &&
                Objects.equals(getUrls(), that.getUrls());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMaterialType(), getTitle(), getText(), getUrls());
    }

    public LocalDateTime getStartDate() {
        return null;
    }

    public void setStartDate(LocalDateTime startDate) {

    }

    public LocalDateTime getDueDate() {
        return null;
    }

    public void setDueDate(LocalDateTime dueDate) {

    }

    public List<Criterion> getCriterions() {
        return null;
    }

    public void setCriterions(List<Criterion> criterions) {

    }

    public List<User> getStudents() {
        return null;
    }

    public void setStudents(List<User> students) {

    }

    public int getMaxScore() {
        return 0;
    }

    public void setMaxScore(int maxScore) {

    }

    public List<Question> getQuestions() {
        return null;
    }

    public void setQuestions(List<Question> questions) {

    }

    public String getTask() {
        return null;
    }

    public void setTask(String task) {

    }

    public String getUrl() {
        return null;
    }

    public void setUrl(String url) {

    }

    @Override
    public String toString() {
        return "MaterialDTO{" +
                "id=" + id +
                ", materialType=" + materialType +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", urls=" + urls +
                '}';
    }

}
