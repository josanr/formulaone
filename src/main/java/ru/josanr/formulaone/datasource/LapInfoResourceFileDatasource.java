package ru.josanr.formulaone.datasource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
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
    public List<String> getAbbreviations() {
        return read(abbreviations);
    }

    @Override
    public List<String> getStartTime() {
        return read(start);
    }

    @Override
    public List<String> getEndTime() {
        return read(end);
    }


    private List<String> read(String fileName) {
        var fileStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (fileStream == null) {
            throw DataSourceExceptions.becauseFileNotFound();
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(fileStream))) {
            Stream<String> lines = br.lines();
            return lines.toList();
        } catch (IOException e) {
            throw DataSourceExceptions.becauseFileReadError();
        }
    }
}
