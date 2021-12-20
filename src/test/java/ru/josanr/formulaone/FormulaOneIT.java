package ru.josanr.formulaone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.josanr.formulaone.datasource.LapInfoResourceFileDatasource;
import ru.josanr.formulaone.formatter.TextTableFormatter;
import ru.josanr.formulaone.repository.RacerLapInfoRepositoryImpl;

class FormulaOneIT {

    @Test
    void formatter_shouldReturnFormattedTextTable_givenInputFiles() {
        var dataSource = new LapInfoResourceFileDatasource("abbreviations.txt", "start.log", "end.log");
        var repository = new RacerLapInfoRepositoryImpl(dataSource, 15);
        var formatter = new TextTableFormatter(repository);

        String expected = getExpectedString();
        Assertions.assertEquals(expected, formatter.format());
    }

    private String getExpectedString() {
        return """
             1. Sebastian Vettel |FERRARI                  |01:04.415
             2. Daniel Ricciardo |RED BULL RACING TAG HEUER|01:12.013
             3. Valtteri Bottas  |MERCEDES                 |01:12.434
             4. Lewis Hamilton   |MERCEDES                 |01:12.460
             5. Stoffel Vandoorne|MCLAREN RENAULT          |01:12.463
             6. Kimi Raikkonen   |FERRARI                  |01:12.639
             7. Fernando Alonso  |MCLAREN RENAULT          |01:12.657
             8. Sergey Sirotkin  |WILLIAMS MERCEDES        |01:12.706
             9. Charles Leclerc  |SAUBER FERRARI           |01:12.829
            10. Sergio Perez     |FORCE INDIA MERCEDES     |01:12.848
            11. Romain Grosjean  |HAAS FERRARI             |01:12.930
            12. Pierre Gasly     |SCUDERIA TORO ROSSO HONDA|01:12.941
            13. Carlos Sainz     |RENAULT                  |01:12.950
            14. Esteban Ocon     |FORCE INDIA MERCEDES     |01:13.028
            15. Nico Hulkenberg  |RENAULT                  |01:13.065
            ---------------------------------------------------------
            16. Brendon Hartley  |SCUDERIA TORO ROSSO HONDA|01:13.179
            17. Marcus Ericsson  |SAUBER FERRARI           |01:13.265
            18. Lance Stroll     |WILLIAMS MERCEDES        |01:13.323
            19. Kevin Magnussen  |HAAS FERRARI             |01:13.393
            """;
    }
}
