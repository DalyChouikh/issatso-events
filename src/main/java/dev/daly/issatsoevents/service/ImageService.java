package dev.daly.issatsoevents.service;

import dev.daly.issatsoevents.entity.ImageData;
import dev.daly.issatsoevents.repository.ImageDataRepository;
import dev.daly.issatsoevents.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageDataRepository imageDataRepository;


    public ImageData uploadImage(MultipartFile file) throws IOException {
        if(file.isEmpty())
            throw new IOException("File is empty");
        if(!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png"))
            throw new IOException("File is not an image");


        return imageDataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
    }

    public byte[] downloadImage(Long imageId){
        Optional<ImageData> dbImageData = imageDataRepository.findById(imageId);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

}
