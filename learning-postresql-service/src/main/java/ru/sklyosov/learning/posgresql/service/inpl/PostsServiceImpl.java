package ru.sklyosov.learning.posgresql.service.inpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sklyosov.learning.posgresql.domain.PostEntity;
import ru.sklyosov.learning.posgresql.persistence.PostsRepository;
import ru.sklyosov.learning.posgresql.persistence.UsersRepository;
import ru.sklyosov.learning.posgresql.service.PostsService;
import ru.sklyosov.learning.postresql.model.PostDto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

@Service
@AllArgsConstructor
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;

    @Override
    public PostDto createPost(String userName, PostDto postDto) {

        var user = usersRepository.findByName(userName);
        if (user == null)
            return null;

        var entity = postsRepository.save(PostEntity.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .user(user)
                .tags(postDto.getTags())
                .createdAt(Instant.now())
                .build());

        return new PostDto()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .userName(userName)
                .tags(entity.getTags())
                .createdAt(OffsetDateTime.ofInstant(entity.getCreatedAt(), ZoneId.systemDefault()));

    }

    @Override
    @Transactional
    public void deletePost(int id) {
        postsRepository.deleteById(id);
    }

    @Override
    public List<PostDto> getPosts(String userName, LocalDate startDate, LocalDate endDate, String searchWord, String tag) {

        OffsetDateTime startTime = startDate == null ? null : OffsetDateTime.of(startDate, LocalTime.NOON, ZoneOffset.UTC);
        OffsetDateTime endTime = endDate == null ? null : OffsetDateTime.of(endDate, LocalTime.NOON, ZoneOffset.UTC);

        List<PostEntity> entities = postsRepository.findAlByParams(userName
                , startTime, endTime
                , searchWord
                , tag
        );
                
        return entities.stream()
                .map(it ->
                        new PostDto()
                                .id(it.getId())
                                .title(it.getTitle())
                                .content(it.getContent())
                                .userName(userName)
                                .tags(it.getTags())
                                .createdAt(OffsetDateTime.ofInstant(it.getCreatedAt(), ZoneId.systemDefault())))
                .toList();
    }
}
