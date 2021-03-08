package edu.cnm.deepdive.deepdivegalleryservice.model.dao;

import edu.cnm.deepdive.deepdivegalleryservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findFirstByOauthKey(String oauthKey);

}
