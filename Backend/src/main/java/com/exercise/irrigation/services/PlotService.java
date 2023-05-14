package com.exercise.irrigation.services;


import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import com.exercise.irrigation.dtos.PlotDTO;
import com.exercise.irrigation.mappers.PlotMapper;
import com.exercise.irrigation.mappers.TimeSlotMapper;
import com.exercise.irrigation.models.Plot;
import com.exercise.irrigation.models.TimeSlot;
import com.exercise.irrigation.repositories.PlotRepository;
import com.exercise.irrigation.repositories.TimeSlotRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlotService {

  
    private final PlotRepository plotRepository;

    private final TimeSlotRepository timeSlotRepository;
 
    private final TimeSlotMapper timeSlotMapper;
    @Autowired
    private final PlotMapper plotMapper;

   @Transactional
    public void addPlot(PlotDTO plotDTO){
        TimeSlot timeSlot = timeSlotRepository.findById(timeSlotMapper.map(plotDTO.getTimeSlot()).getId()).orElse(null);
        if(null == timeSlot){
            timeSlot = new TimeSlot();
        }

        Plot plot = plotMapper.map(plotDTO);

        plot.setTimeSlot(timeSlot);
        plotRepository.save(plot);
    }


    public void updatePlot(PlotDTO plotDTO){
       Plot plot = plotRepository.findByName(plotDTO.getName()).orElse(null);
       Plot updatedPlot = plotMapper.map(plotDTO);
        plot.setName(updatedPlot.getName());
        plot.setArea(updatedPlot.getArea());
        plot.setCropType(updatedPlot.getCropType());
        plot.setWaterRequired(updatedPlot.getWaterRequired());

        TimeSlot timeSlot = timeSlotRepository.findById(timeSlotMapper.map(plotDTO.getTimeSlot()).getId()).orElse(null);

            plot.setTimeSlot(timeSlot);
       
        plotRepository.save(plot);

    }


    public void deletePlot(String name){
        Plot plot = plotRepository.findByName(name).orElseThrow(()-> new EntityNotFoundException("Plot not found with name "+ name));
        plotRepository.delete(plot);
    }


    public List<PlotDTO> findAllPlots(){
        List<Plot> plots = plotRepository.findAll();
        System.out.println(plots);
        return plotMapper.map(plots);
    }

    public PlotDTO findPlotById(int plot_id){
        Optional<Plot> plotOfLand = plotRepository.findById(plot_id);
        return plotMapper.map(plotOfLand.get());
    }


}
