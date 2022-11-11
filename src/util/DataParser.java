/*
 *  It contains a static method to read txt files of all kinds of in-game objects and return arraylists of strings.
 */

package util;

import java.io.*;
import java.util.*;

public class DataParser {

    private static final String DELIMITER = " +";

    public static ArrayList<ArrayList<String>> parse(String path) {
        ArrayList<ArrayList<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(DataParser.class.getClassLoader().getResourceAsStream(path)))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                count += 1;
                if (count == 1) {
                    continue;
                }

                String[] values = line.split(DELIMITER);
                if (values.length == 1) {
                    continue;
                }

                ArrayList<String> newlist = new ArrayList<>();
                Collections.addAll(newlist, values);
                records.add(newlist);
            }
        } catch (IOException e) {
            System.out.println("An error occured when reading " + path);
            throw new RuntimeException(e);
        }
        return records;
    }
}
