package ru.sklyosov.learning.posgresql.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sklyosov.learning.posgresql.domain.PostAuditLogEntity;

import java.util.List;

public interface AuditRepository extends JpaRepository<PostAuditLogEntity, Integer> {

    List<PostAuditLogEntity> findAllByPostId(Integer postId);

}
