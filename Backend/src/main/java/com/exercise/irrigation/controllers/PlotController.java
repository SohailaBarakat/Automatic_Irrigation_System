package com.exercise.irrigation.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exercise.irrigation.dtos.PlotDTO;
import com.exercise.irrigation.exceptions.AlreadyExistException;
import com.exercise.irrigation.exceptions.NotFoundException;
import com.exercise.irrigation.repositories.PlotRepository;
import com.exercise.irrigation.response.ResponseHandler;
import com.exercise.irrigation.services.PlotService;

@RestController
@RequestMapping("/plot")
public class PlotController {


    @Autowired 
    private PlotService plotService;

    @Autowired
    PlotRepository plotRepository;


    @PostMapping
    public ResponseEntity<Object> addPlot( @RequestBody PlotDTO plotDTO){
        if (plotRepository.existsByName(plotDTO.getName())){
            System.out.println("lapla");
            throw new AlreadyExistException("This land is already exist");
        }
            plotService.addPlot(plotDTO);
            return ResponseHandler.generateResponse("Land added successfully!", HttpStatus.valueOf(200),null, true);

        
        
    }

    @PutMapping 
    public ResponseEntity<Object> updatePlot(@RequestBody PlotDTO plotDTO){
        if (!plotRepository.existsByName(plotDTO.getName())){
            throw new NotFoundException("Can't find land with the name " + plotDTO.getName());
        }
        plotService.updatePlot(plotDTO);
        return ResponseHandler.generateResponse("Land updated successfully!", HttpStatus.valueOf(200),null, true);

    }



    @GetMapping
    public List<PlotDTO> findAllPlots(){
        return plotService.findAllPlots();       
    }


    @DeleteMapping("/{name}")
    public ResponseEntity<Object> deletePlot(@PathVariable("name") String plot_name){
        if (!plotRepository.existsByName(plot_name)){
            throw new NotFoundException("Can't find land with the name " + plot_name);
        }
        plotService.deletePlot(plot_name);
        return ResponseHandler.generateResponse("The land has been deleted successfully!", HttpStatus.valueOf(200),null, true);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> findPlotById(@PathVariable("id") int plot_id){
        if(!plotRepository.existsById(plot_id)){
            throw new NotFoundException("The land doesn't exist");

        }
        PlotDTO plotDTO = plotService.findPlotById(plot_id);
        return ResponseHandler.generateResponse("Found the land successfully!", HttpStatus.valueOf(200),plotDTO, true);
    }





    
    
}
