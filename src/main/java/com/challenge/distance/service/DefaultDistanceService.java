package com.challenge.distance.service;

import com.challenge.postcode.model.PostcodeEntity;
import com.challenge.postcode.repository.PostcodeRepository;
import com.challenge.util.DistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultDistanceService implements DistanceService {
    @Autowired
    private PostcodeRepository postcodeRepository;

    @Override
    public String getDistanceByPostcode(String fromPostCode, String toPostcode) throws IllegalArgumentException {
        PostcodeEntity from = postcodeRepository.find(fromPostCode);
        PostcodeEntity to = postcodeRepository.find(toPostcode);
        if(from == null)
            throw new IllegalArgumentException("From postcode not found!");
        if(to == null)
            throw new IllegalArgumentException("To postcode not found!");

        double totalDistance = DistanceCalculator.calculateDistance(from.getLatitude(),from.getLatitude(),to.getLatitude(),to.getLongitude());
        return totalDistance+"km";
    }
}
