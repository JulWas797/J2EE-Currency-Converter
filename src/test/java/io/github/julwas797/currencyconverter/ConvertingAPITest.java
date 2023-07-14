package io.github.julwas797.currencyconverter;

import io.github.julwas797.currencyconverter.service.ConvertingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ConvertingAPITest {

    @Mock
    ConvertingService convertingService;
    @InjectMocks
    ConvertingAPI convertingAPI;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void convert() {
        // Given
        String code = "USD";
        double amount = 2d;
        double expected = 8d;
        when(convertingService.getConverted(anyDouble(), anyString())).thenReturn(expected);
        // When
        String result = convertingAPI.convert(amount, code);
        // Then
        assertEquals(result, String.format("%.2f", expected), "Values should be converted successfully");
    }
}