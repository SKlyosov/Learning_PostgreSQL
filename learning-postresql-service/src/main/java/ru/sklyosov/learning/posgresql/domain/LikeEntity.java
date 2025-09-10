package ru.sklyosov.learning.posgresql.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sklyosov.learning.postresql.model.EmotionEnum;

@Entity
@Table(name = "likes")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(LikeEntityId.class)
public class LikeEntity {

    @Id
    @Column(name = "post_id", nullable = false)
    private Integer postId;

    @Id
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "emotion", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmotionEnum emotion;

}
