package com.exercise.irrigation.services;

import org.springframework.stereotype.Service;
import com.exercise.irrigation.dtos.TimeSlotDTO;
import com.exercise.irrigation.mappers.TimeSlotMapper;
import com.exercise.irrigation.models.TimeSlot;
import com.exercise.irrigation.repositories.TimeSlotRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IrrigationSlotService {

    private final TimeSlotRepository irrigationRepository;
    private final TimeSlotMapper irrigationSlotMapper;


    public void addIrrigationSlot(TimeSlotDTO irrigationSlotDTO){
        final TimeSlot irrigationSlot = irrigationSlotMapper.map(irrigationSlotDTO);
        irrigationRepository.save(irrigationSlot);

    }
    
    
}
