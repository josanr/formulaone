package ru.josanr.formulaone.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.josanr.formulaone.datasource.LapInfoDatasource;
import ru.josanr.formulaone.model.RacerLapInfo;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RacerLapInfoRepositoryTest {

    private static final int QUALIFIED_COUNT = 1;
    private static final int TOTAL_COUNT = 3;
    private LapInfoDatasource inputFileReader;
    private RacerLapInfoRepository parser;

    @Test
    void findAll_returnCombinedList_givenStringData() {
        List<RacerLapInfo> all = parser.findAll();

        Assertions.assertEquals(3, all.size());
        var firstItem = all.get(0);
        var expectedItem = getRacerLapInfoItems().get(0);
        Assertions.assertEquals(expectedItem.getId(), firstItem.getId());
        Assertions.assertEquals(expectedItem.getName(), firstItem.getName());
        Assertions.assertEquals(expectedItem.getTeam(), firstItem.getTeam());
        Assertions.assertEquals(expectedItem.getLapTime(), firstItem.getLapTime());
    }

    @Test
    void findQualified_shouldReturnNotMoreThanQualifiedCount_givenInput() {
        List<RacerLapInfo> qualified = parser.findQualified();
        Assertions.assertEquals(QUALIFIED_COUNT, qualified.size());
    }


    @Test
    void findQualified_shouldReturnEliminated_givenInput() {
        List<RacerLapInfo> eliminated = parser.findEliminated();
        Assertions.assertEquals(TOTAL_COUNT - QUALIFIED_COUNT, eliminated.size());
    }

    @BeforeEach
    void setUp() {
        this.inputFileReader = mock(LapInfoDatasource.class);
        when(inputFileReader.getAbbreviations()).thenReturn(List.of(
            "DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER",
            "SVF_Sebastian Vettel_FERRARI",
            "LHM_Lewis Hamilton_MERCEDES"
        ));

        when(inputFileReader.getStartTime()).thenReturn(List.of(
            "DRR2018-05-24_12:14:12.054",
            "SVF2018-05-24_12:02:58.917",
            "LHM2018-05-24_12:18:20.125"
        ));

        when(inputFileReader.getEndTime()).thenReturn(List.of(
            "DRR2018-05-24_12:15:24.067",
            "SVF2018-05-24_12:04:03.332",
            "LHM2018-05-24_12:19:32.585"
        ));
        this.parser = new RacerLapInfoRepositoryImpl(inputFileReader, QUALIFIED_COUNT);
    }

    private List<RacerLapInfo> getRacerLapInfoItems() {
        RacerLapInfo e1 = new RacerLapInfo("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER");
        e1.setStartTime(LocalDateTime.parse("2018-05-24T12:14:12.054"));
        e1.setEndTime(LocalDateTime.parse("2018-05-24T12:15:24.067"));

        RacerLapInfo e2 = new RacerLapInfo("SVF", "Sebastian Vettel", "FERRARI");
        e2.setStartTime(LocalDateTime.parse("2018-05-24T12:02:58.917"));
        e2.setEndTime(LocalDateTime.parse("2018-05-24T12:04:03.332"));

        RacerLapInfo e3 = new RacerLapInfo("LHM", "Lewis Hamilton", "MERCEDES");
        e3.setStartTime(LocalDateTime.parse("2018-05-24T12:18:20.125"));
        e3.setEndTime(LocalDateTime.parse("2018-05-24T12:19:32.585"));

        return List.of(
            e1, e2, e3
        );
    }
}
