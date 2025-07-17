package tech.wvs.commentsms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tech.wvs.commentsms.api.dto.CommentInput;
import tech.wvs.commentsms.domain.entity.Comment;
import tech.wvs.commentsms.domain.repository.CommentRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository repository;

    public CommentService(CommentRepository commentRepository) {
        this.repository = commentRepository;
    }


    public Comment create(CommentInput dto) {
        //1. receber dto e converter pra entity
        var entity = new Comment(dto);


        //TODO
        //2. validar na api de moderação

        //TODO
        //3. se for aprovado salvar no banco e retorna o objeto

        return repository.save(entity);
    }

    public Optional<Comment> findById(UUID id) {
        return repository.findById(id);
    }

    public Page<Comment> findAll(Integer page, Integer pageSize) {
        var pageRequest = PageRequest.of(page, pageSize);

        return repository.findAll(pageRequest);
    }
}
