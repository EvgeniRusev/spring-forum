package com.forum.dtos.posts;

public class CreatePostDto {
    private String title;

    private String content;

    private String username;

    private String categories;

    public CreatePostDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String category) {
        this.categories = category;
    }
}