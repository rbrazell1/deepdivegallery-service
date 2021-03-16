package edu.cnm.deepdive.deepdivegalleryservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.net.URI;
import java.util.Date;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(
    indexes = {
        @Index(columnList = "created"),
        @Index(columnList = "title")
    }
)
@Component
public class Image {

  private static EntityLinks entityLinks;


  @NonNull
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "image_id", nullable = false, updatable = false, columnDefinition = "CHAR(16) FOR BIT DATA")
  private UUID id;


  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @NonNull
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date updated;

  private String title;

  private String description;

  @NonNull
  @Column(nullable = false, updatable = false)
  private String name;

  @JsonIgnore
  @NonNull
  @Column(nullable = false, updatable = false)
  private String key;

  @NonNull
  @Column(nullable = false, updatable = false)
  private String contentType;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "contributor_id", nullable = false, updatable = false)
  private User contributor;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "gallery_id")
  private Gallery gallery;

  // GETTERS AND SETTERS

  @NonNull
  public UUID getId() {
    return id;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }

  @NonNull
  public Date getUpdated() {
    return updated;
  }

  @NonNull
  public User getContributor(User contributor) {
    return this.contributor;
  }

  public void setContributor(@NonNull User contributor) {
    this.contributor = contributor;
  }

  @NonNull
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Gallery getGallery() {
    return gallery;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  @NonNull
  public String getKey() {
    return key;
  }

  public void setKey(@NonNull String key) {
    this.key = key;
  }

  @NonNull
  public String getContentType() {
    return contentType;
  }

  public void setContentType(@NonNull String content_type) {
    this.contentType = content_type;
  }

  public URI getHref() {
    //noinspection ConstantConditions
    return (id != null) ? entityLinks.linkToItemResource(Image.class, id).toUri() : null;
  }

  @Autowired
  public void setEntityLinks(
      @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") EntityLinks entityLinks) {
    Image.entityLinks = entityLinks;
  }


  @PostConstruct
  private void initHateoas() {
    //noinspection ResultOfMethodCallIgnored
    entityLinks.hashCode();
  }

}
