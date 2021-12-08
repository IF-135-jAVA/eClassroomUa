package com.softserve.betterlearningroom.entity;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Material {

    private Long id;

    private MaterialType materialType;

    private String title;

    private String text;

    private List<Link> urls;

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
}
