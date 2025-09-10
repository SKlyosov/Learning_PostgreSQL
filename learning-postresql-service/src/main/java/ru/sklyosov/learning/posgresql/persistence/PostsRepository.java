package ru.sklyosov.learning.posgresql.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sklyosov.learning.posgresql.domain.PostEntity;

import java.time.OffsetDateTime;
import java.util.List;

public interface PostsRepository extends JpaRepository<PostEntity, Integer> {

    @Query(nativeQuery = true,
        value = """
            select p.*
            from posts p
            left join users u
            on u.id = p.user_id
            where (:userName is null or u.name = :userName) and
            (cast(:startTime as timestamp with time zone) is null or p.created_at >=:startTime) and
            (cast(:endTime as timestamp with time zone) is null or p.created_at <=:endTime) and
            (:searchWord is null or p.content @@ to_tsquery(:searchWord)) and
            (:tag is null or :tag = ANY(tags))
        """)
    List<PostEntity> findAlByParams(String userName
            ,OffsetDateTime startTime, OffsetDateTime endTime
            ,String searchWord
            ,String tag
    );
}
