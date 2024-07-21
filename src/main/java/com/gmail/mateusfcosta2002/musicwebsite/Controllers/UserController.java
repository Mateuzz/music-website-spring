package com.gmail.mateusfcosta2002.musicwebsite.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.UserDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Mappers.UserMapper;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.UserRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Web.Exceptions.NotFoundException;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserController(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public UserDTO show(@PathVariable long id) throws NotFoundException {
        var user = userRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("User of id " + id + " does not exists"));

        return userMapper.createDTO(user);
    }
}
