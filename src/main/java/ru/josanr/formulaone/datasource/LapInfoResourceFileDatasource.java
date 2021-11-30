package ru.josanr.formulaone.datasource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class LapInfoResourceFileDatasource implements LapInfoDatasource {

    private final String abbreviations;
    private final String start;
    private final String end;

    public LapInfoResourceFileDatasource(String abbreviations, String start, String end) {
        this.abbreviations = abbreviations;
        this.start = start;
        this.end = end;
    }
    @Override
    public Stream<String> getAbbreviations() {
        return read(abbreviations);
    }

    @Override
    public Stream<String> getStartTime() {
        return read(start);
    }

    @Override
    public Stream<String> getEndTime() {
        return read(end);
    }

    private Stream<String> read(String fileName) {
        var fileStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (fileStream == null) {
            throw DataSourceExceptions.becauseFileNotFound();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(fileStream));
        return br.lines();
    }
}
