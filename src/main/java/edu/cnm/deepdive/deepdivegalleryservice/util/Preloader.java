package edu.cnm.deepdive.deepdivegalleryservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cnm.deepdive.deepdivegalleryservice.model.entity.Gallery;
import edu.cnm.deepdive.deepdivegalleryservice.model.entity.User;
import edu.cnm.deepdive.deepdivegalleryservice.service.GalleryService;
import edu.cnm.deepdive.deepdivegalleryservice.service.UserService;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Profile("preload")
public class Preloader implements CommandLineRunner {

  public static final String PRELOADER_USERNAME = "System";
  public static final String PRELOADER_OAUTH_KEY = "";
  public static final String PRELOADER_DATA = "preload/galleries.json";

  private final UserService userService;
  private final GalleryService galleryService;

  @Autowired
  public Preloader(UserService userService,
      GalleryService galleryService) {
    this.userService = userService;
    this.galleryService = galleryService;
  }

  @Override
  public void run(String... args) throws Exception {
    User user = userService.getOrCreate(PRELOADER_OAUTH_KEY, PRELOADER_USERNAME);
    ClassPathResource resource = new ClassPathResource(PRELOADER_DATA);
    try (InputStream inputStream = resource.getInputStream()) {
      List<Gallery> galleryList = new LinkedList<>();
      ObjectMapper mapper = new ObjectMapper();
      for (Gallery gallery : mapper.readValue(inputStream, Gallery[].class)) {
        gallery.setContributor(user);
        galleryList.add(gallery);
      }
      galleryService.save(galleryList);
    }
  }
}
