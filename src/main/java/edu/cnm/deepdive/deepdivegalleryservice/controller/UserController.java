package edu.cnm.deepdive.deepdivegalleryservice.controller;


import com.fasterxml.jackson.annotation.JsonView;
import edu.cnm.deepdive.deepdivegalleryservice.model.entity.Image;
import edu.cnm.deepdive.deepdivegalleryservice.model.entity.User;
import edu.cnm.deepdive.deepdivegalleryservice.service.UserService;
import edu.cnm.deepdive.deepdivegalleryservice.view.ImageViews;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@ExposesResourceFor(User.class)
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(
      UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public User me(Authentication authentication) {
    return (User) authentication.getPrincipal();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public User get(@PathVariable("id") UUID id, Authentication authentication) {
    return userService.get(id)
        .orElseThrow();
  }

  @GetMapping(value = "/{id}/{images}", produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonView(ImageViews.Flat.class)
  public Iterable<Image> getImages(@PathVariable UUID id, Authentication auth) {
    return userService.get(id)
        .map(User::getImages)
        .orElseThrow();
  }


}
