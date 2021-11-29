package ru.josanr.formulaone;

import ru.josanr.formulaone.datasource.LapInfoResourceFileDatasource;
import ru.josanr.formulaone.formatter.TextTableFormatter;
import ru.josanr.formulaone.repository.RacerLapInfoRepositoryImpl;

public class Main {

    public static void main(String[] args) {
        var dataSource = new LapInfoResourceFileDatasource("abbreviations.txt", "start.log", "end.log");
        var repository = new RacerLapInfoRepositoryImpl(dataSource, 15);
        var formatter = new TextTableFormatter(repository);

        System.out.println(formatter.format());
    }

}
