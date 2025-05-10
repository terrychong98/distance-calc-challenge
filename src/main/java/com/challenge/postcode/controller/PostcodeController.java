package com.challenge.postcode.controller;

import com.challenge.postcode.model.PostcodeEntity;
import com.challenge.postcode.service.PostcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postcode")
public class PostcodeController {
    @Autowired
    private PostcodeService postcodeService;

    @PostMapping("/update")
    public void update(@RequestBody PostcodeEntity entity) throws IllegalArgumentException {
        postcodeService.update(entity);
    }
}
