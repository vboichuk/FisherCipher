package com.home.controller;

import com.home.dto.RequestDto;
import com.home.model.ImageData;
import com.home.service.FisherCipherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/generate")
public class FisherCipherController {

    private final FisherCipherService service;

    @PostMapping
    public ResponseEntity<byte[]> generate(@RequestBody RequestDto requestDto) {
        System.out.println("requestDto = " + requestDto);
        ImageData imageData = service.generate(requestDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(
                ContentDisposition.attachment()
                        .filename(imageData.getName())
                        .build()
        );
        return new ResponseEntity<>(imageData.getData(), headers, HttpStatus.OK);
    }
}
