package ru.josanr.formulaone.formatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.josanr.formulaone.model.RacerLapInfo;
import ru.josanr.formulaone.repository.RacerLapInfoRepository;

import java.time.LocalDateTime;
import java.util.List;

class TextTableFormatterTest {

    @Test
    void format_shouldReturnTextToPrint_givenListOfRacerLapInfo() {

        var repository = Mockito.mock(RacerLapInfoRepository.class);

        Mockito.when(repository.findAll()).thenReturn(getRacerLapInfoItems());
        Mockito.when(repository.findQualified()).thenReturn(List.of(
            getRacerLapInfoItems().get(0),
            getRacerLapInfoItems().get(1)
        ));

        Mockito.when(repository.findEliminated()).thenReturn(List.of(
            getRacerLapInfoItems().get(2)
        ));

        var expected = """
             1. name1          |team1    |01:05.000
             2. name_max_length|team2    |01:13.000
            ---------------------------------------
             3. name3          |team_long|00:58.000
            """;
        var formatter = new TextTableFormatter(repository);
        var printData = formatter.format();

        Assertions.assertEquals(expected, printData);
    }

    private List<RacerLapInfo> getRacerLapInfoItems() {
        RacerLapInfo e1 = new RacerLapInfo("a1", "name1", "team1");
        e1.setStartTime(LocalDateTime.parse("2018-05-24T12:02:58"));
        e1.setEndTime(LocalDateTime.parse("2018-05-24T12:04:03"));

        RacerLapInfo e2 = new RacerLapInfo("a2", "name_max_length", "team2");
        e2.setStartTime(LocalDateTime.parse("2018-05-24T12:02:49"));
        e2.setEndTime(LocalDateTime.parse("2018-05-24T12:04:02"));

        RacerLapInfo e3 = new RacerLapInfo("a3", "name3", "team_long");
        e3.setStartTime(LocalDateTime.parse("2018-05-24T12:13:04"));
        e3.setEndTime(LocalDateTime.parse("2018-05-24T12:14:02"));

        return List.of(
            e1, e2, e3
        );
    }
}
