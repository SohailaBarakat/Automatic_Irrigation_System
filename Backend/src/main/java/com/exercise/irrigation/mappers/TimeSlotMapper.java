package com.exercise.irrigation.mappers;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.exercise.irrigation.dtos.TimeSlotDTO;
import com.exercise.irrigation.models.TimeSlot;

@Component
@Mapper(componentModel = "spring") 
public interface TimeSlotMapper {
    TimeSlotDTO map(TimeSlot irrigationSlot);
    TimeSlot map(TimeSlotDTO irrigationSlot);


    // List<TimeSlot> map(List<TimeSlotDTO> irrigationSlots);
    List<TimeSlotDTO> map(List<TimeSlot> timeSlots);
}
