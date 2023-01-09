package de.bcxp.challenge;

import java.io.IOException;
import java.util.*;

import static java.lang.Double.POSITIVE_INFINITY;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * Main methods for reading the file and computing the needed values for:
     * Population Density and The temperature spread
     * @param args The CLI arguments passed
     */

    public static String countryWithHighestPopulationDensity(List<Data> data) {
        // find the highest density
        double highestDensity = 0.0;
        double density;
        String countryName = "";
        for (Data row : data) {
            if (row.getValueTwo() < 1) {
                System.err.println("Invalid value Area < 1 in: " + row);
            } else {
                density = row.getValueOne() / (double) row.getValueTwo();
                if (density > highestDensity) {
                    highestDensity = density;
                    countryName = row.getName();
                }
            }
        }
        return countryName;
    }

    public static String dayWithSmallestTempSpread(List<Data> data) {
        // find the smallest temperature spread
        int smallestTempSpread = (int) POSITIVE_INFINITY;
        int tempSpread;
        String day = "";
        for (Data row : data){
            int max = row.getValueOne();
            int min = row.getValueTwo();
            if (max >= min){
                tempSpread = max -  min;
                if (tempSpread < smallestTempSpread){
                    smallestTempSpread = tempSpread;
                    day = row.getName();
                }
            } else {
                System.err.println("Invalid value MxT < MnT in: " + row);
            }
        }
        return day;
    }

    public static void main(String... args) throws IOException {

        String path = "C:/Users/adm-okiddeh/IdeaProjects/programming-challenge/target/classes/de/bcxp/challenge/";

        // Your preparation code …

        // Task 1: Read the file, then output the day number of the day with the smallest temperature spread.
        CSVReader weatherReader = new CSVReader(',');
        List<Data> weatherData =  weatherReader.readData(path +"weather.csv", new String[]{"Day", "MxT", "MnT"});

        String dayWithSmallestTempSpread = dayWithSmallestTempSpread(weatherData);     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);

        // Task 2: Read the file, then output the name of the country with the highest number of people per square kilometre.
        CSVReader countriesReader = new CSVReader(';');
        List<Data> countriesData =  countriesReader.readData(path + "countries.csv", new String[]{"Name", "Population", "Area (km²)"});

        String countryWithHighestPopulationDensity = countryWithHighestPopulationDensity(countriesData); // Your population density analysis function call …
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);


    }

}