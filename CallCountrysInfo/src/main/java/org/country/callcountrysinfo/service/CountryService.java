package org.country.callcountrysinfo.service;

import org.country.callcountrysinfo.domain.Country;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

// Annotate the class with @Service to indicate that it's a service component in the Spring framework
@Service
public class CountryService {
    // Define the API URL as a constant
    private static final String API_URL = "https://restcountries.com/v3.1/all";
    // Declare a WebClient instance to make HTTP requests
    private final WebClient webClient;

    // Constructor for the CountryService class
    public CountryService(WebClient.Builder webClientBuilder) {
        // Initialize the WebClient instance with the API URL
        this.webClient = webClientBuilder.baseUrl(API_URL).build();
    }

    // Method to fetch all countries, calculate their population density, and return them sorted in descending order
    public Mono<List<Map<String, Double>>> getCountriesByPopulationDensity() {
        // Make a GET request to the API URL
        return webClient.get().uri("")
                .retrieve()
                // Convert the response body to a Flux of Country objects
                .bodyToFlux(Country.class)
                // Collect the Country objects into a List
                .collectList()
                // Process the List of Country objects
                .map(countries -> {
                    // Sort the countries in descending order of population density
                    countries.sort((c1, c2) -> Double.compare(c2.getPopDensity(), c1.getPopDensity()));
                    // Initialize a list to store the country densities
                    List<Map<String, Double>> countryDensityList = new ArrayList<>();
                    // Iterate over the countries
                    for (Country country : countries) {
                        // Initialize a map to store the country name and population density
                        Map<String, Double> countryDensity = new HashMap<>();
                        // Get the country name
                        Map<String, Object> nameMap = country.getName();
                        // Check if the name map is not null
                        if (nameMap != null) {
                            // Get the common name of the country
                            String commonName = (String) nameMap.get("common");
                            // Put the country name and population density into the map
                            countryDensity.put(commonName, country.getPopDensity());
                        }
                        // Add the country density map to the list
                        countryDensityList.add(countryDensity);
                    }
                    // Return the list of country densities
                    return countryDensityList;
                })
                // If an error occurs, return an empty list
                .onErrorReturn(Collections.emptyList());
    }

    // Method to get the country in Asia with the most bordering countries of a different region
    public Mono<Country> getAsianCountryWithMostBorders() {
        // Make a GET request to the API URL
        return webClient.get().uri("")
                .retrieve()
                // Convert the response body to a Flux of Country objects
                .bodyToFlux(Country.class)
                // Collect the Country objects into a List
                .collectList()
                // Process the List of Country objects
                .map(countries -> {
                    // Initialize variables to store the country with the most borders and the number of borders
                    Country maxBorderCountry = null;
                    int maxBorders = 0;
                    // Iterate over the countries
                    for (Country country : countries) {
                        // Check if the country is in Asia
                        if ("Asia".equals(country.getRegion())) {
                            // Initialize a variable to store the number of borders
                            int borders = 0;
                            // Iterate over the bordering countries
                            for (String border : country.getBorders()) {
                                // Find the bordering country by its code
                                Country borderCountry = findCountryByCode(countries, border);
                                // Check if the bordering country is not null and is in a different region
                                if (borderCountry != null && !borderCountry.getRegion().equals(country.getRegion())) {
                                    // Increment the number of borders
                                    borders++;
                                }
                            }
                            // Check if the number of borders is greater than the maximum number of borders
                            if (borders > maxBorders) {
                                // Update the maximum number of borders and the country with the most borders
                                maxBorders = borders;
                                maxBorderCountry = country;
                            }
                        }
                    }
                    // Return the country with the most borders
                    return maxBorderCountry;
                })
                // If the Mono is empty, return a new Country object
                .switchIfEmpty(Mono.just(new Country()));
    }

    // Helper method to find a country by its code
    private Country findCountryByCode(List<Country> countries, String code) {
        // Iterate over the countries
        for (Country country : countries) {
            // Check if the country code matches the given code
            if (code.equals(country.getCca3())) {
                // Return the matching country
                return country;
            }
        }
        // If no matching country is found, return null
        return null;
    }
}