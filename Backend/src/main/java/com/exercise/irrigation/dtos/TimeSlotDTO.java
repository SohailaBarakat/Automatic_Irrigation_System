package com.exercise.irrigation.dtos;

import lombok.Data;

@Data
public class TimeSlotDTO {
    private Integer id;
    private int startHour;
    private int endHour;
    private Boolean isCompleted;
    
}
