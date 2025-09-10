package ru.sklyosov.learning.posgresql.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sklyosov.learning.posgresql.domain.LikeEntity;
import ru.sklyosov.learning.posgresql.domain.LikeEntityId;

import java.util.List;

public interface LikesRepository extends JpaRepository<LikeEntity, LikeEntityId> {

    @Query("""
            select l
            from LikeEntity l
            join fetch l.user u
            where l.postId = :postId""")
    List<LikeEntity> findByPostId(int postId);
}
