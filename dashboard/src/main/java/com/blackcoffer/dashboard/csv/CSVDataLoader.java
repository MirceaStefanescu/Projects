package com.blackcoffer.dashboard.csv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class CSVDataLoader implements ApplicationRunner {
    @Value("${csv.file.path}")
    private String csvFilePath;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CSVDataLoader(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        readCSV();
    }

    public void readCSV() throws IOException {
        ClassPathResource resource = new ClassPathResource(csvFilePath);
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int rowCounter = 0;
            while ((line = br.readLine()) != null) {
                rowCounter++;
                if (rowCounter > 1) { // Skip the first row
                    String[] data = line.split(";");
                    handleNullValues(data);
                    parseAndFormatYearValues(data);
                    executeInsertStatement(data);
                }
            }
        }
    }

    private void handleNullValues(String[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null || data[i].isEmpty()) {
                data[i] = null;
            }
        }
    }

    private void parseAndFormatYearValues(String[] data) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM, dd yyyy HH:mm:ss", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        try {
            Integer endYear = (data[0] != null && !data[0].isEmpty()) ? Integer.parseInt(data[0]) : null;
            Integer startYear = (data[10] != null && !data[10].isEmpty()) ? Integer.parseInt(data[10]) : null;
            Date added = (data[12] != null && !data[12].isEmpty()) ? inputFormat.parse(data[12]) : null;
            Date published = (data[13] != null && !data[13].isEmpty()) ? inputFormat.parse(data[13]) :
                    null; // parse 'published' date
            String likelihoodStr =
                    (data[20] != null) ? data[20].replace("\"", "").trim() : ""; // remove double quotes and trim
            int likelihood = (!likelihoodStr.isEmpty()) ? Integer.parseInt(likelihoodStr) : 0; // parse 'likelihood'
            data[0] = (endYear != null) ? String.valueOf(endYear) : null;
            data[10] = (startYear != null) ? String.valueOf(startYear) : null;
            data[12] = (added != null) ? outputFormat.format(added) : null;
            data[13] = (published != null) ? outputFormat.format(published) : ""; // format 'published' date
            data[20] = String.valueOf(likelihood); // set 'likelihood' as string
            // Remove dots from 'citylng' value if it's not null
            if (data[1] != null || data[2] != null) {
                String citylng = data[1].replace(".", "");
                String citylat = data[2].replace(".", "");
                data[1] = citylng;
                data[2] = citylat;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            // This will catch any ParseException that is not caught above
            e.printStackTrace();
        } catch (Exception e) {
            // This will catch any other exceptions that are not caught above
            e.printStackTrace();
        }
    }

    private void executeInsertStatement(String[] data) {
        String insertQueryPrefix = "INSERT INTO dashboard (end_year, citylng, citylat, intensity, sector, topic, " +
                "insight, swot, url, region, start_year, impact, added, published, city, country, " +
                "relevance, pestle, source, title, likelihood) VALUES ";
        String columnPlaceholders = "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String dynamicInsertQuery = insertQueryPrefix + columnPlaceholders;
        jdbcTemplate.update(dynamicInsertQuery, (Object[]) data);
    }
}