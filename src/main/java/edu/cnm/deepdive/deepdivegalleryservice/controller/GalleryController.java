package edu.cnm.deepdive.deepdivegalleryservice.controller;

import edu.cnm.deepdive.deepdivegalleryservice.model.entity.Gallery;
import edu.cnm.deepdive.deepdivegalleryservice.model.entity.User;
import edu.cnm.deepdive.deepdivegalleryservice.service.GalleryService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/galleries")
public class GalleryController {

  private final GalleryService galleryService;

  public GalleryController(
      GalleryService galleryService) {
    this.galleryService = galleryService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Gallery post(@RequestBody Gallery gallery, Authentication auth) {
    return galleryService.newGallery(gallery, (User) auth.getPrincipal());
  }
}
