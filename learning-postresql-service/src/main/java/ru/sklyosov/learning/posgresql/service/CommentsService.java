package ru.sklyosov.learning.posgresql.service;

import ru.sklyosov.learning.postresql.model.CommentDto;

import java.util.List;

public interface CommentsService {

    List<CommentDto> getComments(int postId);

    CommentDto createComment(String userName, CommentDto commentDto);

    void deleteComment(String userName, int id);

}
