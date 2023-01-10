package org.rustamtut;

import org.rustamtut.entity.Building;
import org.rustamtut.service.Parser;
import org.rustamtut.service.impl.CsvParser;
import org.rustamtut.service.impl.XmlParser;
import org.rustamtut.stat.Counter;

import java.io.File;
import java.util.*;

public class Main {
    private static final String STOP = "stop";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String in;
        System.out.println("__________ENTER FILE PATH__________");
        while (!(in = scanner.nextLine()).equals(STOP)) {
            File file;
            if (!(file = new File(in)).exists()) {
                System.out.println("NO FILE BY THIS PATH!\nENTER AGAIN");
                continue;
            }
            Parser parser;
            if (in.endsWith(".csv")) {
                parser = new CsvParser(file);
            } else if (in.endsWith(".xml")) {
                parser = new XmlParser(file);
            } else {
                System.out.println("_WRONG FILE FORMAT!\nENTER AGAIN");
                continue;
            }

            parser.parse();

            List<Building> data = parser.getData();

            Map<Building, Integer> duplicates = Counter.countDuplicates(data);

            Map<String, int[]> floorStat = Counter.countBuildingsByCityAndFloors(data);

            printDuplicates(duplicates);

            System.out.println("_____________");

            printFloorStat(floorStat);

            System.out.println("\n\n__________ENTER NEW FILE PATH_________");


        }
    }

    private static void printDuplicates(Map<Building, Integer> map) {
        System.out.println("---duplicates--");
        for (var pair : map.entrySet()) {
            System.out.println("Repeated  " + pair.getValue() +"; Building: " + pair.getKey());
        }
    }

    private static void printFloorStat(Map<String, int[]> map) {
        System.out.println("---building by floor stat---");
        for (var pair: map.entrySet()) {
            System.out.println("City: " + pair.getKey());
            int[] floors = pair.getValue();
            if (floors[0] != 0)
                System.out.println("\t\t\t1" + " floor : " + floors[0]);
            for (int i = 1; i < floors.length; i++) {
                if (floors[i] != 0)
                    System.out.println("\t\t\t" + (i + 1) + " floors: " + floors[i]);
            }
        }
    }

}