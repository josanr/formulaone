package ru.josanr.formulaone.datasource;

import java.util.stream.Stream;

public interface LapInfoDatasource {

    Stream<String> getAbbreviations();
    Stream<String> getStartTime();
    Stream<String> getEndTime();
}
