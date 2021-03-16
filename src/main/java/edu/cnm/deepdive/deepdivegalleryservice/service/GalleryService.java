package edu.cnm.deepdive.deepdivegalleryservice.service;

import edu.cnm.deepdive.deepdivegalleryservice.model.dao.GalleryRepository;
import edu.cnm.deepdive.deepdivegalleryservice.model.entity.Gallery;
import edu.cnm.deepdive.deepdivegalleryservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {

  private final GalleryRepository galleryRepository;


  public GalleryService(
      GalleryRepository galleryRepository) {
    this.galleryRepository = galleryRepository;
  }

  public Gallery newGallery(Gallery gallery, User contributor) {
    gallery.setContributor(contributor);
    return galleryRepository.save(gallery);
  }

  public Optional<Gallery> get(UUID id) {
    return galleryRepository.findById(id);
  }

  public Iterable<Gallery> getAll() {
    return galleryRepository.getAllByOrderByTitleAsc();
  }

}
