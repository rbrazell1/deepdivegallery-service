package edu.cnm.deepdive.deepdivegalleryservice.controller;


import edu.cnm.deepdive.deepdivegalleryservice.model.dao.UserRepository;
import edu.cnm.deepdive.deepdivegalleryservice.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserRepository userRepository;

  @Autowired
  public UserController(
      UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public User me(Authentication authentication) {
    return (User) authentication.getPrincipal();
  }


}