package ru.sklyosov.learning.posgresql.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.sklyosov.learning.posgresql.service.PostsService;
import ru.sklyosov.learning.postresql.api.PostsApi;
import ru.sklyosov.learning.postresql.model.PostDto;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class PostsController implements PostsApi {

    private final PostsService postsService;

    @Override
    public ResponseEntity<List<PostDto>> getPosts(String userName, LocalDate startDate, LocalDate endDate,
                                                  String searchWord, String tag) {

        return ResponseEntity.ok(postsService.getPosts(userName, startDate, endDate, searchWord, tag));
    }

    @Override
    public ResponseEntity<PostDto> createPost(String user, PostDto postDto) {
        return ResponseEntity.ok(postsService.createPost(user, postDto));
    }

    @Override
    public ResponseEntity<Void> deletePost(Integer id) {
        postsService.deletePost(id);
        return ResponseEntity.ok().build();
    }
}
