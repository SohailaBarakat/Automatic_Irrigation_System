package com.exercise.irrigation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.irrigation.models.TimeSlot;

public interface TimeSlotRepository extends JpaRepository<TimeSlot,Integer>{
    
}
