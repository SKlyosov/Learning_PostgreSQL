package ru.sklyosov.learning.posgresql.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.sklyosov.learning.posgresql.service.LikesService;
import ru.sklyosov.learning.postresql.api.LikesApi;
import ru.sklyosov.learning.postresql.model.LikeDto;

import java.util.List;

@RestController
@AllArgsConstructor
public class LikesController implements LikesApi {

    private final LikesService likesService;

    @Override
    public ResponseEntity<LikeDto> createLike(String user, LikeDto likeDto) {
        likesService.createLike(user, likeDto);
        return ResponseEntity.ok(likesService.createLike(user, likeDto));
    }

    @Override
    public ResponseEntity<Void> deleteLike(String user, LikeDto likeDto) {
        likesService.deleteLike(user, likeDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<LikeDto>> getLikes(Integer postId) {
        return ResponseEntity.ok(likesService.getCLikes(postId));
    }
}
