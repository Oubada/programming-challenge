package de.bcxp.challenge;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVReader implements MyFileReader {
    private char delimiter;

    public CSVReader(char delimiter) {
        this.delimiter = delimiter;
    }

    public char getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }

    /**
     *
     * @param filePath from the resources
     * @param columnNames: The columns that we'd like to have from the file
     * @return The values from CSV file
     * @throws IOException if file do not exist
     */
    public List<Data> readData(String filePath, String[] columnNames) throws IOException {
        // Initialize list of type Data
        List<Data> data = new ArrayList<>();

        try (FileReader file = new FileReader(filePath);
             BufferedReader br = new BufferedReader(file)) {

            if (columnNames.length != 0) {
                readValues(columnNames, data, br);
            } else {
                System.err.println("Data could not be read no columns were provided: " + Arrays.toString(columnNames));
            }
        }
        return data;
    }

    /**
     * Reads the whole CSV file values
     */
    public List<List<String>> readData(String filePath) throws IOException {
        List<List<String>> data = new ArrayList<>();

        try (FileReader file = new FileReader(filePath);
             BufferedReader br = new BufferedReader(file)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(String.valueOf(delimiter));
                List<String> row = new ArrayList<>();
                Collections.addAll(row, values);
                data.add(row);
            }
        }
        return data;
    }

    /**
     * Reads the values and checks for invalid inputs
     * @throws IOException + NumberFormatException
     */

    private void readValues(String[] columnNames, List<Data> data, BufferedReader br) throws IOException {
        // Parse CSV file
        CSVParser parser = CSVFormat.EXCEL.withDelimiter(delimiter).withHeader().parse(br);
        //NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        // get values from each row
        for (CSVRecord record : parser) {
            String name = record.get(columnNames[0]);
            int valueOne; int valueTwo;
            try {
                valueOne = Integer.parseInt(record.get(columnNames[1]));
                valueTwo = Integer.parseInt(record.get(columnNames[2]));
            } catch (NumberFormatException e) {
                System.err.println("Invalid data type in row: " + record.getRecordNumber() + ": " + e.getMessage());
                continue;
            }
            Data row = new Data(columnNames, name, valueOne, valueTwo);
            data.add(row);
        }
        parser.close();
    }

}
