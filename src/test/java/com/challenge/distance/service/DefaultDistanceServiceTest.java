package com.challenge.distance.service;

import com.challenge.postcode.model.PostcodeEntity;
import com.challenge.postcode.repository.PostcodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultDistanceServiceTest {

    @Mock
    private PostcodeRepository postcodeRepository;

    @InjectMocks
    private DefaultDistanceService distanceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDistanceByPostcode_success() {
        PostcodeEntity from = new PostcodeEntity();
        from.setLatitude(51.5074); // example: London
        from.setLongitude(-0.1278);

        PostcodeEntity to = new PostcodeEntity();
        to.setLatitude(53.4808); // example: Manchester
        to.setLongitude(-2.2426);

        when(postcodeRepository.find("SW1A1AA")).thenReturn(from);
        when(postcodeRepository.find("M11AE")).thenReturn(to);

        String result = distanceService.getDistanceByPostcode("SW1A1AA", "M11AE");

        assertTrue(result.endsWith("km"));
        assertFalse(result.startsWith("0")); // crude sanity check
        verify(postcodeRepository, times(1)).find("SW1A1AA");
        verify(postcodeRepository, times(1)).find("M11AE");
    }

    @Test
    void testGetDistanceByPostcode_fromNotFound() {
        when(postcodeRepository.find("INVALID")).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            distanceService.getDistanceByPostcode("INVALID", "M11AE");
        });

        assertEquals("From postcode not found!", exception.getMessage());
    }

    @Test
    void testGetDistanceByPostcode_toNotFound() {
        PostcodeEntity from = new PostcodeEntity();
        from.setLatitude(51.5074);
        from.setLongitude(-0.1278);

        when(postcodeRepository.find("SW1A1AA")).thenReturn(from);
        when(postcodeRepository.find("INVALID")).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            distanceService.getDistanceByPostcode("SW1A1AA", "INVALID");
        });

        assertEquals("To postcode not found!", exception.getMessage());
    }
}
