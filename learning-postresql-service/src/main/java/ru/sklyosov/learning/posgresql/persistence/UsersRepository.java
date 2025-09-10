package ru.sklyosov.learning.posgresql.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import ru.sklyosov.learning.posgresql.domain.UserEntity;

public interface UsersRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByName(String user);

    @Modifying
    void deleteByName(String userName);

}
