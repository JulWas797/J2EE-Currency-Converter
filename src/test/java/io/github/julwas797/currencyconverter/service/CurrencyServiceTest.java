package io.github.julwas797.currencyconverter.service;

import io.github.julwas797.currencyconverter.exceptions.APIException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyServiceTest {
    CurrencyService currencyService = new CurrencyService();

    @Test
    void getCourse() throws Throwable {
        // Given
        double course = 4d;
        String json = String.format(Locale.US, "{\"table\":\"A\",\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"rates\":[{\"no\":\"135/A/NBP/2023\",\"effectiveDate\":\"2023-07-14\",\"mid\":%.2f}]}", course);
        String badJson = "{}abc";
        // When
        double output = executeRatesMethod(json);
        // Then
        assertEquals(output, course, "Should return good courses");
        assertThrows(APIException.class, () -> executeRatesMethod(badJson), "Should throw error for bad input");
    }

    private double executeRatesMethod(String arg) throws Throwable {
        Method readRatesMethod = CurrencyService.class.getDeclaredMethod("readRatesFromJson", String.class);
        readRatesMethod.setAccessible(true);
        try {
            return (double) readRatesMethod.invoke(currencyService, arg);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}