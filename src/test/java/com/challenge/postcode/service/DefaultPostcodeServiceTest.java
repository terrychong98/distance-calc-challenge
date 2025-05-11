package com.challenge.postcode.service;

import com.challenge.postcode.model.PostcodeEntity;
import com.challenge.postcode.repository.PostcodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultPostcodeServiceTest {

    @Mock
    private PostcodeRepository postcodeRepository;

    @InjectMocks
    private DefaultPostcodeService postcodeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdate_NullEntity_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            postcodeService.update(null);
        });

        assertEquals("Postcode entity is null!", exception.getMessage());
    }

    @Test
    void testUpdate_BlankPostcode_ThrowsException() {
        PostcodeEntity entity = new PostcodeEntity();
        entity.setPostcode("   "); // blank or whitespace

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            postcodeService.update(entity);
        });

        assertEquals("Postcode is blank!", exception.getMessage());
    }

    @Test
    void testUpdate_PostcodeNotFound_ThrowsException() throws IOException {
        PostcodeEntity entity = new PostcodeEntity();
        entity.setPostcode("AB123CD");

        when(postcodeRepository.exists("AB123CD")).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            postcodeService.update(entity);
        });

        assertEquals("Postcode not found!", exception.getMessage());
        verify(postcodeRepository, times(1)).exists("AB123CD");
        verify(postcodeRepository, never()).update(any());
    }

    @Test
    void testUpdate_ValidEntity_CallsRepositoryUpdate() throws IOException {
        PostcodeEntity entity = new PostcodeEntity();
        entity.setPostcode("AB123CD");

        when(postcodeRepository.exists("AB123CD")).thenReturn(true);

        postcodeService.update(entity);

        verify(postcodeRepository, times(1)).exists("AB123CD");
        verify(postcodeRepository, times(1)).update(entity);
    }
}
