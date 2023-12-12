package org.country.callcountrysinfo.controller;

import org.country.callcountrysinfo.domain.Country;
import org.country.callcountrysinfo.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

// Annotate the class with @RestController to indicate that it's a RESTful web service controller
@RestController
public class CountryController {
    // Declare a CountryService instance to handle the business logic
    private final CountryService countryService;

    // Constructor for the CountryController class
    public CountryController(CountryService countryService) {
        // Initialize the CountryService instance
        this.countryService = countryService;
    }

    // Endpoint to get the population density of all countries
    @GetMapping("/countries/population-density")
    public Mono<List<Map<String, Double>>> getCountriesByPopulationDensity() {
        // Call the getCountriesByPopulationDensity() method of the CountryService instance
        // and return its result
        return countryService.getCountriesByPopulationDensity();
    }

    // Endpoint to get the country in Asia with the most bordering countries of a different region
    @GetMapping("/countries/asia/most-borders")
    public Mono<Country> getAsianCountryWithMostBorders() {
        // Call the getAsianCountryWithMostBorders() method of the CountryService instance
        // and return its result
        return countryService.getAsianCountryWithMostBorders();
    }
}