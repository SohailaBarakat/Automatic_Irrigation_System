package com.exercise.irrigation.repositories;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.exercise.irrigation.models.Plot;



public interface PlotRepository extends JpaRepository<Plot,Integer> {
    Optional<Plot> findByName(String plotName);
    void deleteByName(String name);
    Boolean existsByName(String name);

    
   
    
}
