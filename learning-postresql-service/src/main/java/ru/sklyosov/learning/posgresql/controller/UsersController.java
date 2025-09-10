package ru.sklyosov.learning.posgresql.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.sklyosov.learning.posgresql.service.UsersService;
import ru.sklyosov.learning.postresql.api.UsersApi;
import ru.sklyosov.learning.postresql.model.UserDto;

@RestController
@AllArgsConstructor
public class UsersController implements UsersApi {

    private final UsersService usersService;

    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        return ResponseEntity.ok(usersService.createUser(userDto));
    }

    @Override
    public ResponseEntity<Void> deleteLUser(String userName) {
        usersService.deleteUser(userName);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserDto> getUser(String userName) {
        return ResponseEntity.ok(usersService.getUser(userName));
    }
}
