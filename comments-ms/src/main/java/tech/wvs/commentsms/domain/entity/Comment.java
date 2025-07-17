package tech.wvs.commentsms.domain.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import tech.wvs.commentsms.api.dto.CommentInput;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String text;
    private String author;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Comment() {
    }

    public Comment(UUID id, String text, String author, LocalDateTime createdAt) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.createdAt = createdAt;
    }

    public Comment(CommentInput dto) {
        this.id = null;
        this.text = dto.text();
        this.author = dto.author();
        this.createdAt = null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
