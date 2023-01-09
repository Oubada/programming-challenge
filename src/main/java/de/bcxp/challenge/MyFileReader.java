package de.bcxp.challenge;

import java.io.IOException;
import java.util.List;

public interface MyFileReader <T> {
    List<T> readData(String filePath, String[] columnNames) throws IOException;
}
