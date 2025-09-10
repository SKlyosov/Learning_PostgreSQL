package ru.sklyosov.learning.posgresql.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LikeEntityId implements Serializable {

    private Integer postId;

    private UserEntity user;


}
