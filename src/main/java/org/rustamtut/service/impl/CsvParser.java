package org.rustamtut.service.impl;

import org.rustamtut.entity.Building;
import org.rustamtut.service.Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class CsvParser implements Parser {

    private final File file;
    private List<Building> data;

    private static final Logger logger = Logger.getLogger(CsvParser.class.getName());

    public CsvParser(File file) {
        this.file = file;
    }

    @Override
    public void parse() {
        data = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            logger.info("Starting CSV file parsing");
            String sep = ";";
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(sep);
                data.add(new Building(split[0], split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3])));
            }
            logger.info("Finishing CSV file parsing. Handled " + data.size() + " items.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Building> getData() {
        return data;
    }

}
