package com.exercise.irrigation.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.irrigation.dtos.TimeSlotDTO;
import com.exercise.irrigation.exceptions.NotFoundException;
import com.exercise.irrigation.response.ResponseHandler;
import com.exercise.irrigation.services.IrrigationSlotService;

@RestController
@RequestMapping("/irrigation")
public class IrrigationSlotController {

    @Autowired
   private IrrigationSlotService irrigationSlotService;
    

   @PostMapping
   public ResponseEntity<Object> addIrrigationSlot(@RequestBody TimeSlotDTO timeSlotDTO){
    if(timeSlotDTO.getStartHour()<0 || timeSlotDTO.getEndHour()>23){
        throw new NotFoundException("Please enter a valid time");
    }
    irrigationSlotService.addIrrigationSlot(timeSlotDTO);
    return ResponseHandler.generateResponse("Time slot added successfully!", HttpStatus.valueOf(200),null, true);

   }
}
