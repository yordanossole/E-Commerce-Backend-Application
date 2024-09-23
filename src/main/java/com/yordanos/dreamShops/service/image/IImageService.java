package com.yordanos.dreamShops.service.image;

import com.yordanos.dreamShops.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById (Long id);
    void deleteImageById (Long id);
    Image saveImage(List<MultipartFile> file, Long productId);
    void updateImage(MultipartFile file, Long imageId);
}
