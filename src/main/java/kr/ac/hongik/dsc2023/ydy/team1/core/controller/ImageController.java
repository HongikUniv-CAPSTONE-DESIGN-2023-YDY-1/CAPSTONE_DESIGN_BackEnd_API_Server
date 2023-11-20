package kr.ac.hongik.dsc2023.ydy.team1.core.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Value("${item.img-path}")
    private String IMG_PATH;

    @GetMapping(value = "/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getItemImage(@PathVariable String name) throws IOException {
        String imgPath = IMG_PATH + File.separator + name;
        FileInputStream fileInputStream = new FileInputStream(imgPath);
        byte[] img = IOUtils.toByteArray(fileInputStream);
        fileInputStream.close();
        return new ResponseEntity<>(img, HttpStatus.OK);
    }
}
