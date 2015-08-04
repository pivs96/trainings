package com.exadel.service;

import com.exadel.model.entity.training.Reserve;

import java.util.List;

public interface ReserveService {

    List<Reserve> getAllReservesByTrainingId(long trainingId);

    void addReserve(Reserve participation);

    Reserve getReserveById(String id);

    Reserve getReserveById(long id);

    Reserve getNextReserve(String id);
}
