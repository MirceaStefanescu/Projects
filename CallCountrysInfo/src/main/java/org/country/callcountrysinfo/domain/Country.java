package org.country.callcountrysinfo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

// Ignore any unknown properties when deserializing JSON to a Country object
@JsonIgnoreProperties(ignoreUnknown = true)
// Use Lombok to generate getters, setters, equals, hashCode and toString methods automatically
@Data
public class Country {
    // A map to store the name data of the country
    private Map<String, Object> name;
    // The 3-letter country code
    private String cca3;
    // The area of the country
    private Double area;
    // The population of the country
    private Double population;
    // The region of the country
    private String region;
    // A list to store the codes of the bordering countries
    private List<String> borders;

    // Method to calculate the population density of the country
    public Double getPopDensity() {
        // Check if the population and area are not null and the area is not zero
        if (population != null && area != null && area != 0) {
            // Calculate and return the population density
            return population / area;
        }
        // If the population or area is null, or the area is zero, return null
        return null;
    }

    // Method to get the list of bordering countries
    public List<String> getBorders() {
        // Check if the borders list is not null
        if (borders != null) {
            // If the borders list is not null, return it
            return borders;
        }
        // If the borders list is null, return an empty list
        return Collections.emptyList();
    }
}