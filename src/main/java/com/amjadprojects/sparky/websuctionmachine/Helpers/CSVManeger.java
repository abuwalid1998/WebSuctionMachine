package com.amjadprojects.sparky.websuctionmachine.Helpers;

import com.amjadprojects.sparky.websuctionmachine.Models.MongoModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


@Service
public class CSVManeger {

    public String CreateCSVFILE(String fileName, List<MongoModel> modelList){

        String csvFilePath = fileName;

        try (FileWriter writer = new FileWriter(csvFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

            // Write the CSV header
            csvPrinter.printRecord("Tag","DATA");

            for (MongoModel object : modelList) {
                csvPrinter.printRecord(object.getTag(),object.getBytes());
            }

            return "CSV file created successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


}
