package ru.sklyosov.learning.posgresql.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sklyosov.learning.postresql.model.OperationEnum;

import java.time.OffsetDateTime;

@Entity
@Table(name = "posts_audit_log")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostAuditLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "post_id", nullable = false)
    private Integer postId;

    @Column(name = "new_title")
    private String newTitle;

    @Column(name = "old_title", nullable = false)
    private String oldTitle;

    @Column(name = "operation", nullable = false)
    @Enumerated(EnumType.STRING)
    private OperationEnum operation;

    @Column(name = "processed_at", nullable = false)
    private OffsetDateTime processedAt = OffsetDateTime.now();

}
