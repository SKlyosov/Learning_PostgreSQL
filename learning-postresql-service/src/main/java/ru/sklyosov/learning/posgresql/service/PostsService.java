package ru.sklyosov.learning.posgresql.service;

import ru.sklyosov.learning.postresql.model.PostDto;

import java.time.LocalDate;
import java.util.List;

public interface PostsService {

    PostDto createPost(String userName, PostDto postDto);

    void deletePost(int id);

    List<PostDto> getPosts(String userName, LocalDate startDate, LocalDate endDate, String searchWord, String tag);

}
