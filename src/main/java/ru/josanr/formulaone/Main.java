package ru.josanr.formulaone;

import ru.josanr.formulaone.datasource.LapInfoResourceFileDatasource;
import ru.josanr.formulaone.formatter.TextTableFormatter;
import ru.josanr.formulaone.repository.RacerLapInfoRepositoryImpl;

public class Main {

    public static final int QUALIFIED_COUNT = 15;

    public static void main(String[] args) {
        var dataSource = new LapInfoResourceFileDatasource("abbreviations.txt", "start.log", "end.log");
        var repository = new RacerLapInfoRepositoryImpl(dataSource, QUALIFIED_COUNT);
        var formatter = new TextTableFormatter(repository);

        System.out.println(formatter.format());
    }

}
