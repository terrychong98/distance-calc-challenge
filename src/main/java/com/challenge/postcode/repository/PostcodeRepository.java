package com.challenge.postcode.repository;

import com.challenge.postcode.model.PostcodeEntity;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.springframework.util.StringUtils;

@Repository
public class PostcodeRepository {
    private static HashMap<String, PostcodeEntity> map = new HashMap<>();
    private static Reader reader = null;
    private static final String[] HEADERS = {"id", "postcode", "latitude", "longitude"};
    private static final String CSV_FILE = "/ukpostcodes.csv";

    //To read data from csv after bean initialized
    @PostConstruct
    public void init() throws IOException {
        try {
            reader = new InputStreamReader(getClass().getResourceAsStream(CSV_FILE));
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                PostcodeEntity entity = new PostcodeEntity(
                        Long.parseLong(record.get(HEADERS[0])),
                        record.get(HEADERS[1]),
                        Double.parseDouble(StringUtils.isEmpty(record.get(HEADERS[2])) ? "0" : record.get("latitude")),
                        Double.parseDouble(StringUtils.isEmpty(record.get(HEADERS[3])) ? "0" : record.get("longitude")));
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

    public void update(PostcodeEntity entity) {
        map.get(entity.getPostcode()).setLatitude(entity.getLatitude());
        map.get(entity.getPostcode()).setLongitude(entity.getLongitude());
        try {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            // Write all records back to the file
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_FILE));
                 CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(HEADERS))) {

                for (Map.Entry<String, PostcodeEntity> entry : map.entrySet()) {
                    csvPrinter.printRecord(entry.getValue().getId(), entry.getValue().getPostcode(), entry.getValue().getLatitude(), entry.getValue().getLongitude());
                }
            }
        } catch (Exception e){

        }
    }
}
