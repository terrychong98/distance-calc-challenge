package com.challenge.postcode.repository;

import com.challenge.postcode.model.PostcodeEntity;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

import org.apache.commons.csv.CSVFormat;
import io.micrometer.common.util.StringUtils;

@Repository
public class PostcodeRepository {
    private static HashMap<String, PostcodeEntity> map = new HashMap<>();
    private static final String[] HEADERS = {"id", "postcode", "latitude", "longitude"};

    @Value("${csv.file}")
    private String CSV_FILE;

    //To read data from csv after bean initialized
    @PostConstruct
    public void init() throws IOException {
        try {
            Reader reader = new InputStreamReader(new FileInputStream(CSV_FILE));
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                PostcodeEntity entity = new PostcodeEntity(
                        Long.parseLong(record.get(HEADERS[0])),
                        record.get(HEADERS[1]),
                        Double.parseDouble(StringUtils.isBlank(record.get(HEADERS[2])) ? "0" : record.get(HEADERS[2])),
                        Double.parseDouble(StringUtils.isBlank(record.get(HEADERS[3])) ? "0" : record.get(HEADERS[3])));
                map.put(entity.getPostcode(), entity);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public PostcodeEntity find(String postcode) {
        try {
            return map.get(postcode);
        } catch (Exception e){
            return null;
        }
    }

    public boolean exists(String postcode) {
        return map.containsKey(postcode);
    }

    public void update(PostcodeEntity entity) throws IOException {
        map.get(entity.getPostcode()).setLatitude(entity.getLatitude());
        map.get(entity.getPostcode()).setLongitude(entity.getLongitude());
        try {
            Path tempFile = Files.createTempFile("tempPostcodes", ".csv");
            // Write all records back to the file
            try (BufferedWriter writer = Files.newBufferedWriter(tempFile);
                 CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(HEADERS))) {

                for (Map.Entry<String, PostcodeEntity> entry : map.entrySet()) {
                    csvPrinter.printRecord(entry.getValue().getId(), entry.getValue().getPostcode(), entry.getValue().getLatitude(), entry.getValue().getLongitude());
                }
                File csvFile = new File(CSV_FILE);
                System.out.println(csvFile.toPath());
                Files.move(tempFile, csvFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
