package de.bcxp.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static de.bcxp.challenge.App.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Example JUnit 5 test case.
 */
class AppTest {
    private String path;
    private String[] weatherColumns;
    private String[] countriesColumns;
    private CSVReader myFileReader;
    private String filePath;

    @BeforeEach
    void setUp() {
        path = "C:/Users/adm-okiddeh/IdeaProjects/programming-challenge/target/classes/de/bcxp/challenge/";
        countriesColumns = new String[]{"Name", "Population", "Area (km²)"};
        weatherColumns = new String[]{"Day", "MxT", "MnT"};
    }

    @Test
    void csvReaderWithoutColumnsTest() throws IOException {
        myFileReader = new CSVReader(',');
        filePath = path + "test1.csv";
        List<List<String>> dataActual = myFileReader.readData(filePath);
        List<List<String>> dataExpected = new ArrayList<>();
        List<String> row1 = new ArrayList<>();
        List<String> row2 = new ArrayList<>();
        row1.add("Day"); row1.add("MxT");
        row2.add("1"); row2.add("88");
        dataExpected.add(row1); dataExpected.add(row2);

        assertEquals(dataExpected, dataActual, "CSVReader did not read data as expected");
    }
    @Test
    void fileNotFoundExceptionTest() {
        String testFile = "csvPath";
        myFileReader = new CSVReader(',');

        Exception exception = assertThrows(Exception.class, () -> {
            myFileReader.readData(testFile, weatherColumns);
            myFileReader.readData(testFile);
        });

        String expectedMessage = "Das System kann die angegebene Datei nicht finden";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void invalidColumnNameExceptionTest() {
        weatherColumns[1] = "Dax";
        myFileReader = new CSVReader(',');
        filePath = path + "weather.csv";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            myFileReader.readData(filePath, weatherColumns);
        });

        String expectedMessage = "Mapping for " + weatherColumns[1] +" not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void emptyColumnNamesTest() throws IOException {
        String[] emptyColumns = new String[]{};
        myFileReader = new CSVReader(',');
        filePath = path + "weather.csv";

        List<Data> data = new ArrayList<>();

        assertEquals(data, myFileReader.readData(filePath, emptyColumns), "Wrong Day");
    }

    @Test
    void invalidValuesTest() throws IOException {
        myFileReader = new CSVReader(',');
        filePath = path + "test2.csv";
        List<Data> test2Data = myFileReader.readData(filePath, weatherColumns);

        assertEquals("2", dayWithSmallestTempSpread(test2Data), "Wrong Day");
    }

    @Test
    void inputPopulationDensityTest() {
        //getDayWithSmallestTempSpread
        List<Data> exampleList = new ArrayList<>();
        Data row1 = new Data(countriesColumns,"Germany", 83200000, 357588);
        Data row2 = new Data(countriesColumns,"France", 67750000, 551695);
        exampleList.add(row1); exampleList.add(row2);
        // correct input
        assertEquals("Germany", countryWithHighestPopulationDensity(exampleList), "Wrong country");

        // invalid input
        exampleList.get(0).setValueTwo(0);
        assertEquals("France", countryWithHighestPopulationDensity(exampleList), "Wrong country");
    }

    @Test
    void inputTempSpreadTest() {
        List<Data> exampleList = new ArrayList<>();
        Data row1 = new Data(weatherColumns,"1", 34, 22);
        Data row2 = new Data(weatherColumns,"2", 33, 15);
        // correct input
        exampleList.add(row1); exampleList.add(row2);
        assertEquals("1", dayWithSmallestTempSpread(exampleList), "Wrong Day");

        // invalid input
        exampleList.get(0).setValueTwo(77);
        assertNotEquals("1", dayWithSmallestTempSpread(exampleList), "Wrong Day");

    }

}