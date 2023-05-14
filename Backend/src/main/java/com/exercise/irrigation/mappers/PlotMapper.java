package com.exercise.irrigation.mappers;






import java.util.List;

import org.mapstruct.Mapper;
import com.exercise.irrigation.dtos.PlotDTO;
import com.exercise.irrigation.models.Plot;

@Mapper(componentModel = "spring") 
public interface PlotMapper {

    Plot map(PlotDTO plot);
    PlotDTO map(Plot plot);
    List<PlotDTO> map(List<Plot> plots);
   

    
}
