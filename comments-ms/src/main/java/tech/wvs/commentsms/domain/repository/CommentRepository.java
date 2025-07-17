package tech.wvs.commentsms.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.wvs.commentsms.domain.entity.Comment;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}