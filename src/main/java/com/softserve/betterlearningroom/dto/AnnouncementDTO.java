package com.softserve.betterlearningroom.dto;

import com.softserve.betterlearningroom.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO {
    private long id;
    private String text;
    private List<Comment> comments;

//    public AnnouncementDTO() {
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        AnnouncementDTO that = (AnnouncementDTO) o;
//        return id == that.id &&
//                Objects.equals(text, that.text);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, text);
//    }
//
//    @Override
//    public String toString() {
//        return "Announcement{" +
//                "id=" + id +
//                ", text='" + text + '\'' +
//                '}';
//    }
}
