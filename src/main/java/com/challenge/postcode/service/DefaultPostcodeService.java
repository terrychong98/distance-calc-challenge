package com.challenge.postcode.service;

import com.challenge.postcode.model.PostcodeEntity;
import com.challenge.postcode.repository.PostcodeRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultPostcodeService implements PostcodeService{

    @Autowired
    private PostcodeRepository postcodeRepository;

    @Override
    public void update(PostcodeEntity entity) throws IllegalArgumentException {
        if(entity == null)
            throw new IllegalArgumentException("Postcode entity is null!");

        if(StringUtils.isBlank(entity.getPostcode()))
            throw new IllegalArgumentException("Postcode is blank!");

        if(!postcodeRepository.exists(entity.getPostcode()))
            throw new IllegalArgumentException("Postcode not found!");

        postcodeRepository.update(entity);
    }
}
