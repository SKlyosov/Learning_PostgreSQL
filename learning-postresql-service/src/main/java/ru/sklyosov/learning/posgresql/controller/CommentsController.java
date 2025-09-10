package ru.sklyosov.learning.posgresql.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.sklyosov.learning.posgresql.service.CommentsService;
import ru.sklyosov.learning.postresql.api.CommentsApi;
import ru.sklyosov.learning.postresql.model.CommentDto;

import java.util.List;

@AllArgsConstructor
@RestController

public class CommentsController implements CommentsApi {

    private final CommentsService commentsService;

    @Override
    public ResponseEntity<CommentDto> createComment(String user, CommentDto commentDto) {
        return ResponseEntity.ok(commentsService.createComment(user, commentDto));
    }

    @Override
    public ResponseEntity<Void> deleteComment(String user, Integer id) {
        commentsService.deleteComment(user, id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CommentDto>> getComments(Integer postId) {
        return ResponseEntity.ok(commentsService.getComments(postId));
    }
}
