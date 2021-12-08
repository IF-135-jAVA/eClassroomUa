package com.softserve.betterlearningroom.dto;

import com.softserve.betterlearningroom.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private long id;
    private User author;
    private String text;
    private LocalDateTime date;

//    public CommentDTO() {
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
//    public User getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(User author) {
//        this.author = author;
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
//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        CommentDTO comment = (CommentDTO) o;
//        return id == comment.id &&
//                Objects.equals(text, comment.text) &&
//                Objects.equals(date, comment.date);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, text, date);
//    }
//
//    @Override
//    public String toString() {
//        return "Comment{" +
//                "id=" + id +
//                ", text='" + text + '\'' +
//                ", date=" + date +
//                '}';
//    }
}
