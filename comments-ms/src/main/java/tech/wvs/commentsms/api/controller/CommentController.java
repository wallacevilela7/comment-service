package tech.wvs.commentsms.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.wvs.commentsms.api.dto.CommentInput;
import tech.wvs.commentsms.api.dto.CommentOutput;
import tech.wvs.commentsms.api.pagination.ApiResponse;
import tech.wvs.commentsms.api.pagination.PaginationResponse;
import tech.wvs.commentsms.domain.entity.Comment;
import tech.wvs.commentsms.service.CommentService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/comments")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CommentOutput> create(@RequestBody CommentInput dto) {

        var comment = service.create(dto);

        return null;
    }

    @GetMapping()
    private ResponseEntity<ApiResponse<CommentOutput>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value= "pageSize", defaultValue = "5") Integer pageSize) {
        var content = service.findAll(page, pageSize);

        return ResponseEntity.ok(
                new ApiResponse(
                        content.getContent()
                                .stream()
                                .map(c -> convertToOutput(c))
                                .toList(),
                        new PaginationResponse(
                                content.getNumber(),
                                content.getSize(),
                                content.getTotalElements(),
                                content.getTotalPages()
                        )
                )
        );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CommentOutput> findById(@PathVariable("id") UUID id) {
        var comment = service.findById(id);

        return comment.isPresent()
                ? ResponseEntity.status(HttpStatus.CREATED).body(convertOptionalToOutput(comment))
                : ResponseEntity.notFound().build();
    }

    private CommentOutput convertOptionalToOutput(Optional<Comment> comment) {
        return new CommentOutput(
                comment.get().getId(),
                comment.get().getAuthor(),
                comment.get().getText(),
                comment.get().getCreatedAt()
        );
    }

    private CommentOutput convertToOutput(Comment comment) {
        return new CommentOutput(
                comment.getId(),
                comment.getAuthor(),
                comment.getText(),
                comment.getCreatedAt()
        );
    }
}
