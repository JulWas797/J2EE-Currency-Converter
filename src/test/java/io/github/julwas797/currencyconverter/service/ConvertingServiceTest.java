package io.github.julwas797.currencyconverter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ConvertingServiceTest {
    @Mock
    CurrencyService currencyService;
    @InjectMocks
    ConvertingService convertingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getConverted() throws Exception {
        // Given
        String code = "USD";
        double course = 3d;
        double ammount = 5d;
        when(currencyService.getCourse(anyString())).thenReturn(course);
        // When
        double result = convertingService.getConverted(ammount, code);
        // Then
        assertEquals(result, ammount / course, "Course should be calculated correctly");
    }
}