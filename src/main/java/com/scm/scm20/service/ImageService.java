package com.scm.scm20.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(MultipartFile contactPicture);
    String getUrlFromPublicId(String publicId);
}
