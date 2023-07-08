package io.github.julwas797.currencyconverter.service;

import io.github.julwas797.currencyconverter.exceptions.ConvertingException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Named("currencyConverterCore")
public class ConvertingService {

    @Inject
    private CurrencyService currencyService;

    public double getConverted(double amount, String code) throws ConvertingException {
        try {
            double currentCourse = currencyService.getCourse(code);
            return amount / currentCourse;
        } catch (Exception e) {
            throw new ConvertingException();
        }
    }
}
