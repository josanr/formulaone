package ru.josanr.formulaone.formatter;

import ru.josanr.formulaone.model.RacerLapInfo;
import ru.josanr.formulaone.repository.RacerLapInfoRepository;

import java.time.Duration;
import java.util.List;
import java.util.OptionalInt;

public class TextTableFormatter implements QualificationFormatter {

    private static final int DEFAULT_COLUMN_WIDTH = 15;
    private final RacerLapInfoRepository repository;
    private int nameColumnWidth;
    private int teamColumnWidth;

    public TextTableFormatter(RacerLapInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public String format() {

        var result = new StringBuilder();
        var orderNumber = 1;

        var allRacers = repository.findAll();
        nameColumnWidth = calculateNameColumnWidth(allRacers);
        teamColumnWidth = calculateTeamColumnWidth(allRacers);
        int fullTableWidth = 15 + nameColumnWidth + teamColumnWidth;

        var qualified = repository.findQualified();
        for (RacerLapInfo lapInfo : qualified) {
            result.append(formatLine(lapInfo, orderNumber)).append("\n");
            orderNumber++;
        }
        result.append("-".repeat(fullTableWidth)).append("\n");
        var eliminated = repository.findEliminated();
        for (RacerLapInfo racerLapInfo : eliminated) {
            result.append(formatLine(racerLapInfo, orderNumber)).append("\n");
            orderNumber++;
        }

        return result.toString();
    }

    private int calculateTeamColumnWidth(List<RacerLapInfo> all) {
        OptionalInt maxTeamLength  = all.stream()
            .map(RacerLapInfo::getTeam)
            .mapToInt(String::length)
            .max();
        if(maxTeamLength.isPresent()) {
            return maxTeamLength.getAsInt();
        }

        return DEFAULT_COLUMN_WIDTH;
    }

    private int calculateNameColumnWidth(List<RacerLapInfo> all) {
        OptionalInt maxNameLength  = all.stream()
            .map(RacerLapInfo::getName)
            .mapToInt(String::length)
            .max();
        if(maxNameLength.isPresent()) {
            return maxNameLength.getAsInt();
        }

        return DEFAULT_COLUMN_WIDTH;
    }

    private String formatLine(RacerLapInfo racer, int index) {
        var line = new StringBuilder();
        String name = racer.getName();
        String team = racer.getTeam();
        return line
            .append(String.format("%1$2s", index))
            .append(". ")
            .append(name)
            .append(" ".repeat(nameColumnWidth - name.length()))
            .append("|")
            .append(team).append(" ".repeat(teamColumnWidth - team.length()))
            .append("|")
            .append(formatLapTime(racer.getLapTime())).toString();
    }

    private String formatLapTime(Duration lapTime) {
        long minutes = lapTime.toMinutesPart();
        long seconds = lapTime.toSecondsPart();
        long milliseconds = lapTime.toMillisPart();
        return String.format("%02d:%02d.%03d", minutes, seconds, milliseconds);
    }
}
