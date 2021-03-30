package edu.cnm.deepdive.deepdivegalleryservice.service;

import edu.cnm.deepdive.deepdivegalleryservice.model.dao.ImageRepository;
import edu.cnm.deepdive.deepdivegalleryservice.model.entity.Gallery;
import edu.cnm.deepdive.deepdivegalleryservice.model.entity.Image;
import edu.cnm.deepdive.deepdivegalleryservice.model.entity.User;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

  private static final String UNTITLED_FILENAME = "untitled";

  private final ImageRepository imageRepository;
  private final StorageService storageService;

  @Autowired
  public ImageService(ImageRepository imageRepository, StorageService storageService) {
    this.imageRepository = imageRepository;
    this.storageService = storageService;
  }

  public Optional<Image> get(@NonNull UUID id) {
    return imageRepository.findById(id);
  }

  public Iterable<Image> list() {
    return imageRepository.findAllByOrderByCreatedDesc();
  }

  public void delete(@NonNull Image image) throws IOException {
    storageService.delete(image.getKey());
    imageRepository.delete(image);
  }

  public Image save(@NonNull Image image) {
    return imageRepository.save(image);
  }

  public Resource getContent(@NonNull Image image) throws IOException {
    return storageService.retrieve(image.getKey());
  }

  public Image store(@NonNull MultipartFile file,
      String title,
      String description,
      @NonNull User contributor,
      Gallery gallery)
      throws IOException, HttpMediaTypeException {
    String originalFilename = file.getOriginalFilename();
    String contentType = file.getContentType();
    String key = storageService.store(file);
    Image image = new Image();
    image.setTitle(title);
    image.setDescription(description);
    image.setContributor(contributor);
    image.setName((originalFilename) != null ? originalFilename : UNTITLED_FILENAME);
    image.setContentType((contentType != null)
        ? contentType
        : MediaType.APPLICATION_OCTET_STREAM_VALUE);
    image.setKey(key);
    image.setGallery(gallery);
    return save(image);
  }

}
