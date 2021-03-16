package edu.cnm.deepdive.deepdivegalleryservice.service;

import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface StorageService {

  String store(MultipartFile file) throws IOException, HttpMediaTypeException;

  Resource retrieve(String key) throws IOException;

  boolean delete(String key) throws IOException, UnsupportedOperationException, SecurityException;
}
