package ru.josanr.formulaone.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class RacerLapInfoTest {

    public static final String ID = "DRR";
    public static final String NAME = "Daniel Ricciardo";
    public static final String TEAM = "RED BULL RACING TAG HEUER";

    @Test
    void testToString_shouldReturnCorrectFormat_givenInputWithNullTime() {

        var expected = "RacerLapInfo{" +
            "id='" + ID + '\'' +
            ", name='" + NAME + '\'' +
            ", team='" + TEAM + '\'' +
            ", startTime=" +
            ", endTime=" +
            '}';

        var lapInfo = new RacerLapInfo(ID, NAME, TEAM);

        Assertions.assertEquals(expected, lapInfo.toString());
    }

    @Test
    void testToString_shouldReturnCorrectFormat_givenInputWithTimeSet() {

        var nowTime = LocalDateTime.now();
        var expected = "RacerLapInfo{" +
            "id='" + ID + '\'' +
            ", name='" + NAME + '\'' +
            ", team='" + TEAM + '\'' +
            ", startTime=" + nowTime +
            ", endTime=" + nowTime +
            '}';

        var lapInfo = new RacerLapInfo(ID, NAME, TEAM);
        lapInfo.setStartTime(nowTime);
        lapInfo.setEndTime(nowTime);
        Assertions.assertEquals(expected, lapInfo.toString());
    }
}
