package com.exercise.irrigation.services;

import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.exercise.irrigation.dtos.PlotDTO;
import com.exercise.irrigation.dtos.TimeSlotDTO;
import com.exercise.irrigation.mappers.PlotMapper;
import com.exercise.irrigation.mappers.TimeSlotMapper;
import com.exercise.irrigation.models.Plot;
import com.exercise.irrigation.models.TimeSlot;
import com.exercise.irrigation.repositories.PlotRepository;
import com.exercise.irrigation.repositories.TimeSlotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class irrigatePlotService {
    
    private final PlotRepository plotRepository;
    @Autowired
    private final PlotMapper plotMapper;

    private final TimeSlotRepository timeSlotRepository;

    @Autowired
    private final TimeSlotMapper timeSlotMapper;

    private int retryCount = 0;
    private final int maxRetries = 5;
    

    @Transactional
    @Scheduled(cron = "0 0 * * * *") // Run every hour
    public void execute() {
        List<Plot> plots = plotRepository.findAll();
        List<PlotDTO> plotDTOs = plotMapper.map(plots);
        for(PlotDTO plot : plotDTOs){
            TimeSlot timeSlot =plotRepository.findById(plot.getId()).get().getTimeSlot();
            LocalTime now  = LocalTime.now();
            
            if(now.isAfter(LocalTime.of(timeSlot.getStartHour(),0)) && now.isBefore(LocalTime.of(timeSlot.getEndHour(),0)) && !timeSlot.getIsCompleted()){
                try{
                    // Try to irrigate plot
                    irrigatPlot(plot);

                    //Update slot status
                    timeSlot.setIsCompleted(true);
                    timeSlotRepository.save(timeSlot);
                    retryCount=0;
                }catch(Exception e){
                    retryCount++;
                    if(retryCount <= maxRetries){
                        irrigatPlot(plot);
                    }else{
                        // Send alert
                        sendAlert();
                    }
                }

               
            }
           
        }
        }


        
        
        private void irrigatPlot(PlotDTO plot) {
            // It's time to irrigate this plot
            //Send request to sensors
            System.out.println("Irrigating plot: " + plot.getName());
        }



        private void sendAlert() {
            // Send alert that irrigation failed
        }



        @Scheduled(cron = "0 0 0 * * *") // Runs at midnight every day
        public void resetTimeSlots() {
            System.out.println("Resetting all time slots to false");
            List<TimeSlot> timeSlots = timeSlotRepository.findAll();
            List<TimeSlotDTO> timeSlotDTOs = timeSlotMapper.map(timeSlots);

            // resetting all time slots to false (not irrigated yet)
            for (TimeSlotDTO timeSlotDTO : timeSlotDTOs) {        
                    timeSlotDTO.setIsCompleted(false);
                    TimeSlot timeSlot = timeSlotMapper.map(timeSlotDTO);
                    timeSlotRepository.save(timeSlot);
             
            }
        }


    }
    
    
