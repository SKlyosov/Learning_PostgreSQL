package ru.sklyosov.learning.posgresql.service.inpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sklyosov.learning.posgresql.domain.CommentEntity;
import ru.sklyosov.learning.posgresql.persistence.CommentsRepository;
import ru.sklyosov.learning.posgresql.persistence.UsersRepository;
import ru.sklyosov.learning.posgresql.service.CommentsService;
import ru.sklyosov.learning.postresql.model.CommentDto;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;
    private final UsersRepository usersRepository;

    @Override
    public List<CommentDto> getComments(int postId) {
        return commentsRepository.findAllByPostId(postId).stream()
                .map( it ->
                        new CommentDto()
                                .id(it.getId())
                                .user(it.getUser().getName())
                                .postId(it.getPostId())
                                .content(it.getContent())
                                .createdAt(OffsetDateTime.ofInstant(it.getCreatedAt(), ZoneId.systemDefault()))
                        ).toList();
    }

    @Override
    @Transactional
    public CommentDto createComment(String userName, CommentDto commentDto) {

        var user = usersRepository.findByName(userName);
        if (user == null)
            return null;

        var entity = commentsRepository.save(CommentEntity.builder()
                        .postId(commentDto.getPostId())
                        .user(user)
                        .content(commentDto.getContent())
                        .createdAt(Instant.now())
                        .build());

        return new CommentDto()
                .id(entity.getId())
                .postId(commentDto.getPostId())
                .user(entity.getUser().getName())
                .content(entity.getContent())
                .createdAt(OffsetDateTime.ofInstant(entity.getCreatedAt(), ZoneId.systemDefault()));
    }

    @Override
    @Transactional
    public void deleteComment(String userName, int id) {
        commentsRepository.deleteByUSerAndId(userName, id);
    }
}
