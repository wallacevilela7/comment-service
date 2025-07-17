package tech.wvs.commentsms.api.dto;

import tech.wvs.commentsms.domain.entity.Comment;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentOutput(
        UUID id,
        String author,
        String text,
        LocalDateTime createdAt) {

    public CommentOutput toOutput(Comment entity) {
        return new CommentOutput(
                entity.getId(),
                entity.getAuthor(),
                entity.getText(),
                entity.getCreatedAt()
        );
    }
}
