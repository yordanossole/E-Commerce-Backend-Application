package com.yordanos.dreamShops.service.image;

import com.yordanos.dreamShops.dto.ImageDto;
import com.yordanos.dreamShops.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById (Long id);
    void deleteImageById (Long id);
    List<ImageDto> saveImages(List<MultipartFile> file, Long productId);
    Image updateImage(MultipartFile file, Long imageId);

    ImageDto convertToDto(Image image);
}
