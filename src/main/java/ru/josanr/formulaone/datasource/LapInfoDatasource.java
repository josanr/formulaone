package ru.josanr.formulaone.datasource;

import java.util.List;

public interface LapInfoDatasource {

    List<String> getAbbreviations();
    List<String> getStartTime();
    List<String> getEndTime();
}
