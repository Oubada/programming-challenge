package de.bcxp.challenge;

/**
 * Data Class to store the needed values for the task
 */
public class Data {
    private  String[] columnNames;
    private String name;
    private int valueOne;
    private int valueTwo;

    public Data(String[] columnNames, String name, int valueOne, int valueTwo) {
        this.columnNames = columnNames;
        this.name = name;
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValueOne() {
        return valueOne;
    }

    public void setValueOne(int valueOne) {
        this.valueOne = valueOne;
    }

    public int getValueTwo() {
        return valueTwo;
    }

    public void setValueTwo(int valueTwo) {
        this.valueTwo = valueTwo;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public String toString() {
        return "Data{" +
                columnNames[0]+": " + name + ", " +
                columnNames[1]+": " + valueOne + ", " +
                columnNames[2]+": " + valueTwo +
                '}';
    }

}
