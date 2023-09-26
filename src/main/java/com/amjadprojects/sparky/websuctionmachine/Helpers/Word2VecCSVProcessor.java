package com.amjadprojects.sparky.websuctionmachine.Helpers;

import com.amjadprojects.sparky.websuctionmachine.Interfaces.CustomSequenceIterator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class Word2VecCSVProcessor {




    public  void processCSVWithWord2Vec(String inputFilePath, String outputFilePath) throws Exception {




        // Load the CSV file
        List<String> lines = new ArrayList<>();
        try (CSVParser csvParser = CSVParser.parse(new File(inputFilePath), StandardCharsets.UTF_8, CSVFormat.DEFAULT)) {
            for (CSVRecord csvRecord : csvParser) {
                lines.add(csvRecord.get(0)); // Assuming the header is in the first column
            }
        }

        // Tokenize the text data (assuming it's space-separated)
        TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
        List<List<String>> sentences = new ArrayList<>();
        for (String line : lines) {
            Tokenizer tokenizer = tokenizerFactory.create(line);
            List<String> tokens = tokenizer.getTokens();
            sentences.add(tokens);
        }

        // Train a Word2Vec model on the text data
        CustomSequenceIterator customSequenceIterator = new CustomSequenceIterator(sentences);

        Word2Vec word2Vec = new Word2Vec.Builder()
                .minWordFrequency(1)
                .iterations(5)
                .layerSize(100)
                .seed(42)
                .windowSize(5)
                .tokenizerFactory(tokenizerFactory)
                .iterate(customSequenceIterator) // Use your CustomSequenceIterator
                .build();

        word2Vec.fit();


        // Create a new header for the Word2Vec data
        String newHeader = "Word2Vec Data";

        // Update the CSV file with Word2Vec vectors
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(outputFilePath), CSVFormat.DEFAULT)) {
            csvPrinter.printRecord(newHeader);
            for (String line : lines) {
                List<String> tokens = tokenizerFactory.create(line).getTokens();
                double[] vector = new double[word2Vec.getLayerSize()];
                for (String token : tokens) {
                    vector = VectorUtils.addVectors(vector, word2Vec.getWordVector(token));
                }
                csvPrinter.printRecord(Arrays.toString(vector));
            }
        }

        System.out.println("Word2Vec data has been saved to " + outputFilePath + " with the new header '" + newHeader + "'.");
    }

    // Helper class for vector operations
    private static class VectorUtils {
        public static double[] addVectors(double[] vector1, double[] vector2) {
            double[] result = new double[vector1.length];
            for (int i = 0; i < vector1.length; i++) {
                result[i] = vector1[i] + vector2[i];
            }
            return result;
        }
    }
}
