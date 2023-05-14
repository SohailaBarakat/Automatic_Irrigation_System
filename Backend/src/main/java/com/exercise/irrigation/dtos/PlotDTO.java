package com.exercise.irrigation.dtos;

import lombok.Data;

@Data
public class PlotDTO {
    private Integer id;
    private TimeSlotDTO timeSlot;
    private String name;
    private double area;
    private double waterRequired;
    private String cropType;
}
