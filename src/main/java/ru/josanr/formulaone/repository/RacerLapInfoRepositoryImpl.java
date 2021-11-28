package ru.josanr.formulaone.repository;

import ru.josanr.formulaone.datasource.LapInfoDatasource;
import ru.josanr.formulaone.model.RacerLapInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RacerLapInfoRepositoryImpl implements RacerLapInfoRepository {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
    private final LapInfoDatasource datasource;
    private final Integer qualifiedCount;
    private List<RacerLapInfo> racersLaps;

    public RacerLapInfoRepositoryImpl(LapInfoDatasource datasource, Integer qualifiedCount) {
        this.datasource = datasource;
        this.qualifiedCount = qualifiedCount;
    }

    @Override
    public List<RacerLapInfo> findAll() {
        racersLaps = getIndexedRacerLap(datasource);
        fillStartData(datasource);
        fillEndData(datasource);
        return racersLaps;
    }

    @Override
    public List<RacerLapInfo> findQualified() {
        return findAll().stream()
            .sorted(Comparator.comparing(RacerLapInfo::getLapTime))
            .limit(qualifiedCount)
            .toList();
    }

    @Override
    public List<RacerLapInfo> findEliminated() {
        return findAll().stream()
            .sorted(Comparator.comparing(RacerLapInfo::getLapTime))
            .skip(qualifiedCount)
            .toList();
    }

    private Optional<RacerLapInfo> getRacerById(String index) {
        return racersLaps.stream().filter(item -> index.equals(item.getId())).findFirst();
    }

    private void fillEndData(LapInfoDatasource reader) {
        var list = reader.getEndTime();
        list.forEach(value -> {
            var index = parseIndex(value);
            var racerLap = this.getRacerById(index);
            racerLap.ifPresent(racerLapInfo -> racerLapInfo.setEndTime(parseTime(value)));
        });
    }

    private void fillStartData(LapInfoDatasource reader) {
        var list = reader.getStartTime();
        list.forEach(value -> {
            var index = parseIndex(value);
            var racerLap = this.getRacerById(index);
            racerLap.ifPresent(racerLapInfo -> racerLapInfo.setStartTime(parseTime(value)));
        });
    }

    private List<RacerLapInfo> getIndexedRacerLap(LapInfoDatasource reader) {
        var abbreviationList = reader.getAbbreviations();

        return abbreviationList.stream()
            .map(line -> {
                var items = line.split("_");
                return new RacerLapInfo(items[0], items[1], items[2]);
            }).toList();
    }

    private LocalDateTime parseTime(String value) {
        var timeStr = value.substring(3);
        return LocalDateTime.parse(timeStr, formatter);
    }

    private String parseIndex(String value) {
        return value.substring(0, 3);
    }
}
