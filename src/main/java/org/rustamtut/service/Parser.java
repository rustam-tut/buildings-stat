package org.rustamtut.service;

import org.rustamtut.entity.Building;


import java.util.List;

public interface Parser {

    void parse();

    List<Building> getData();
}
