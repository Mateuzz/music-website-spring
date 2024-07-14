package com.gmail.mateusfcosta2002.musicwebsite.Controllers;

import java.util.stream.Stream;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinksDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.UserDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Util.LinksDTOUtils;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.UserRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Security.Services.UserService;
import com.gmail.mateusfcosta2002.musicwebsite.Web.Exceptions.NotFoundException;

record UserIndexResponse(
    Stream<UserDTO> users,
    LinksDTO links
) {}

@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;
    private UserService userService;
        
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDTO show(@PathVariable long id) throws NotFoundException {
        var user = userRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("User of id " + id + " does not exists"));

        return userService.createDTO(user);
    }

    @GetMapping
    @Transactional(readOnly = true)
    public UserIndexResponse index() {
        var users = userRepository
            .findAllStreamBy()
            .map(userService::createDTO);

        return new UserIndexResponse(users, LinksDTO.withCanonical(userService.getRootLink()));
    }
}
