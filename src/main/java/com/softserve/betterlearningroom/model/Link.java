package com.softserve.betterlearningroom.model;

import java.util.Objects;

public class Link {

    private Long id;

    private String url;

    private String text;

    public Link(Long id, String url, String text) {
        this.id = id;
        this.url = url;
        this.text = text;
    }

    public Link() { }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return getId().equals(link.getId()) &&
                Objects.equals(getUrl(), link.getUrl()) &&
                Objects.equals(getText(), link.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUrl(), getText());
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
