package com.challenge.postcode.controller;

import com.challenge.postcode.model.PostcodeEntity;
import com.challenge.postcode.service.PostcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/postcode")
public class PostcodeController {
    @Autowired
    private PostcodeService postcodeService;

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody PostcodeEntity entity) throws IllegalArgumentException, IOException {
        try {
            postcodeService.update(entity);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
