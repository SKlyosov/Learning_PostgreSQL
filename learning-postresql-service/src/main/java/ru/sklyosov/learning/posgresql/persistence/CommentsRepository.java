package ru.sklyosov.learning.posgresql.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.sklyosov.learning.posgresql.domain.CommentEntity;

import java.util.List;

public interface CommentsRepository extends JpaRepository<CommentEntity, Integer> {

    @Query("""
            select c
            from CommentEntity c
            join fetch user u
            where c.postId = :postId
            """)
    List<CommentEntity> findAllByPostId(int postId);

    @Modifying
    @Query("""
            delete CommentEntity
            where id = :id and user.name = :userName
            """)
    void deleteByUSerAndId(String userName, int id);
}
