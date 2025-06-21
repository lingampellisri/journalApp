package net.engineeringdigest.journalApp.Service;


import net.engineeringdigest.journalApp.api.response.weatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class weatherService {

    private static  final String apikey="bcace8620938ed3ca1bd1950ef665a02";
    private static  final String API="https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;


    public  weatherResponse getWeather(String city)
    {
        String finalAPI=API.replace("API_KEY",apikey).replace("CITY",city);

      ResponseEntity<weatherResponse>response= restTemplate.exchange(finalAPI, HttpMethod.GET,null, weatherResponse.class);

        weatherResponse body=response.getBody();
        return body;


    }
}

