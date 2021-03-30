package edu.cnm.deepdive.deepdivegalleryservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ImageNotFoundException extends ResponseStatusException {

  private static final String NOT_FOUND = "Image not found";

  public ImageNotFoundException() {
    super(HttpStatus.NOT_FOUND, NOT_FOUND);
  }
}
