package ru.sklyosov.learning.posgresql.service.inpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sklyosov.learning.posgresql.domain.LikeEntity;
import ru.sklyosov.learning.posgresql.domain.LikeEntityId;
import ru.sklyosov.learning.posgresql.persistence.LikesRepository;
import ru.sklyosov.learning.posgresql.persistence.UsersRepository;
import ru.sklyosov.learning.posgresql.service.LikesService;
import ru.sklyosov.learning.postresql.model.LikeDto;

import java.util.List;

@Service
@AllArgsConstructor
public class LikesServiceImpl implements LikesService {

    private final LikesRepository likesRepository;
    private final UsersRepository usersRepository;

    @Override
    public List<LikeDto> getCLikes(int postId) {
        return likesRepository.findByPostId(postId).stream()
                .map( it ->
                        new LikeDto()
                                .postId(it.getPostId())
                                .emotion(it.getEmotion())
                                .userName(it.getUser().getName()))
                .toList();

    }

    @Override
    @Transactional
    public LikeDto createLike(String userName, LikeDto likeDto) {
        var user = usersRepository.findByName(userName);
        if (user == null)
            return null;

        var like = likesRepository.save(
            LikeEntity.builder()
                .postId(likeDto.getPostId())
                .user(user)
                .emotion(likeDto.getEmotion())
                .build());

        return new LikeDto()
                .postId(like.getPostId())
                .userName(like.getUser().getName())
                .emotion(like.getEmotion());
    }

    @Override
    @Transactional
    public void deleteLike(String userName, LikeDto likeDto) {
        var user = usersRepository.findByName(userName);
        if (user == null)
            return;
        likesRepository.deleteById(new LikeEntityId(likeDto.getPostId(), user));

    }
}
