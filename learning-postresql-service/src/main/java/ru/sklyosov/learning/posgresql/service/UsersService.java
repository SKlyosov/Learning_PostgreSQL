package ru.sklyosov.learning.posgresql.service;

import ru.sklyosov.learning.postresql.model.UserDto;

public interface UsersService {

    UserDto createUser(UserDto userDto);

    UserDto getUser(String userName);

    void deleteUser(String userName);

}
