package edu.cnm.deepdive.deepdivegalleryservice.model.dao;

import edu.cnm.deepdive.deepdivegalleryservice.model.entity.Image;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, UUID> {

  // TODO add search methods

}
