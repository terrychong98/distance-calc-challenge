package com.challenge.postcode.service;

import com.challenge.postcode.model.PostcodeEntity;

import java.io.IOException;

public interface PostcodeService {
    void update(PostcodeEntity entity) throws IOException;
}
