package io.github.julwas797.currencyconverter.service;

import io.github.julwas797.currencyconverter.exceptions.APIException;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

@Singleton
@Named("currencyService")
public class CurrencyService {
    public double getCourse(String currencyCode) throws Exception {
        URL nbpApiUrl = new URL("http://api.nbp.pl/api/exchangerates/rates/a/".concat(currencyCode));
        HttpURLConnection nbpApiConnection = (HttpURLConnection) nbpApiUrl.openConnection();
        nbpApiConnection.setRequestMethod("GET");
        nbpApiConnection.setRequestProperty("Accept", "application/json");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(nbpApiConnection.getInputStream()))) {
            String sourceData = reader.lines().collect(Collectors.joining("\n"));
            return readRatesFromJson(sourceData);
        } catch (Exception e) {
            throw new APIException();
        }
    }

    private double readRatesFromJson(String source) throws APIException {
        try (JsonReader jsonReader = Json.createReader(new StringReader(source))) {
            JsonObject jsonObject = jsonReader.readObject();
            return jsonObject
                    .getJsonArray("rates")
                    .getJsonObject(0)
                    .getJsonNumber("mid")
                    .doubleValue();
        } catch (Exception e) {
            throw new APIException();
        }
    }
}
