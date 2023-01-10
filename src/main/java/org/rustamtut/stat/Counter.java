package org.rustamtut.stat;

import org.rustamtut.entity.Building;

import java.util.*;
import java.util.stream.Collectors;

public class Counter {

    public static Map<Building, Integer> countDuplicates(List<Building> data) {
        Map<Building, Integer> frequencies = new HashMap<>();
        for (Building building : data) {
            Integer count = frequencies.get(building);
            frequencies.put(building, (count == null) ? 1 : count + 1);
        }

        return frequencies.entrySet().stream().filter(
                e -> e.getValue() > 1
        ).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue));
    }


    public static Map<String, int[]> countBuildingsByCityAndFloors(List<Building> data) {
        Map<String, Set<Building>> buildingsByCity = new HashMap<>();
        for (Building building : data) {
            String city = building.getCity();
            Set<Building> bbc = buildingsByCity.get(city);
            if (bbc == null) {
                bbc = new HashSet<>();
            }
            bbc.add(building);
            buildingsByCity.put(city, bbc);
        }

        Map<String, int[]> result = new HashMap<>();

        for (Map.Entry<String, Set<Building>> pair : buildingsByCity.entrySet()) {
            int[] floorsNums = new int[5];
            Set<Building> buildings = pair.getValue();
            for (int i = 0; i < 5; i++) {
                int fi = i;
                floorsNums[i] += (int) buildings.stream().filter(b -> b.getFloor() == fi + 1).count();
            }
            result.put(pair.getKey(), floorsNums);
        }
        return result;
    }

}
