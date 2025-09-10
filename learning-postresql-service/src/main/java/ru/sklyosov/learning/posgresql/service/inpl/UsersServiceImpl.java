package ru.sklyosov.learning.posgresql.service.inpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sklyosov.learning.posgresql.domain.UserEntity;
import ru.sklyosov.learning.posgresql.persistence.UsersRepository;
import ru.sklyosov.learning.posgresql.service.UsersService;
import ru.sklyosov.learning.postresql.model.UserDto;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        var entity = usersRepository.save(
            UserEntity.builder()
                    .name(userDto.getName())
                    .email(userDto.getEmail())
                    .build()
                );

        return new UserDto()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail());
    }

    @Override
    public UserDto getUser(String userName) {

        var entity = usersRepository.findByName(userName);

        return entity == null ? null :
            new UserDto()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail());
    }

    @Override
    @Transactional
    public void deleteUser(String userName) {
        usersRepository.deleteByName(userName);
    }
}
