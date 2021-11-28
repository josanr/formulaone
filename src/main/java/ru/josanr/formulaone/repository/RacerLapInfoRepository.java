package ru.josanr.formulaone.repository;

import ru.josanr.formulaone.model.RacerLapInfo;

import java.util.List;

public interface RacerLapInfoRepository {

    List<RacerLapInfo> findAll();

    List<RacerLapInfo> findQualified();

    List<RacerLapInfo> findEliminated();
}
