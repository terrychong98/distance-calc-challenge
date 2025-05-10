package com.challenge.distance.controller;

import com.challenge.distance.service.DistanceService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/distance")
public class DistanceController {

    @Autowired
    private DistanceService distanceService;

    @GetMapping("/postcode")
    public ResponseEntity<String> getDistanceByPostcode(@PathParam("from") String from, @PathParam("to") String to)
            throws IllegalArgumentException  {
        try {
            String distance = distanceService.getDistanceByPostcode(from, to);
            return ResponseEntity.ok(distance);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //can calculate distance using other unique indicators
    //for example: address
}
