package edu.cnm.deepdive.deepdivegalleryservice.model.dao;

import edu.cnm.deepdive.deepdivegalleryservice.model.entity.Gallery;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface GalleryRepository extends CrudRepository<Gallery, UUID> {


}
