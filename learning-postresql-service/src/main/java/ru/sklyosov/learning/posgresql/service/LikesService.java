package ru.sklyosov.learning.posgresql.service;

import ru.sklyosov.learning.postresql.model.LikeDto;

import java.util.List;

public interface LikesService {

    List<LikeDto> getCLikes(int post_id);

    LikeDto createLike(String userName, LikeDto likeDto);

    void deleteLike(String userName, LikeDto likeDto);

}
