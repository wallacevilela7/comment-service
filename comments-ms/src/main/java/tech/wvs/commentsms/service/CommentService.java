package tech.wvs.commentsms.service;

import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tech.wvs.commentsms.api.client.ModerationServiceClient;
import tech.wvs.commentsms.api.dto.CommentInput;
import tech.wvs.commentsms.api.web.ModerationInput;
import tech.wvs.commentsms.api.web.ModerationOutput;
import tech.wvs.commentsms.domain.entity.Comment;
import tech.wvs.commentsms.domain.repository.CommentRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository repository;
    private final ModerationServiceClient moderationServiceClient;

    public CommentService(CommentRepository commentRepository,
                          ModerationServiceClient client) {
        this.repository = commentRepository;
        this.moderationServiceClient = client;
    }


    public Comment create(CommentInput dto) {
        //1. receber dto e converter pra entity
        var entity = new Comment(dto);

        //2. validar na api de moderação
        ModerationInput request = new ModerationInput(entity.getText());
        ModerationOutput response = moderationServiceClient.validadeComment(request);

        //3. se for aprovado salvar no banco e retorna o objeto
        if (response.approved()) {
            repository.save(entity);
        }

        return entity;
    }

    public Optional<Comment> findById(UUID id) {
        return repository.findById(id);
    }

    public Page<Comment> findAll(Integer page, Integer pageSize) {
        var pageRequest = PageRequest.of(page, pageSize);

        return repository.findAll(pageRequest);
    }
}
