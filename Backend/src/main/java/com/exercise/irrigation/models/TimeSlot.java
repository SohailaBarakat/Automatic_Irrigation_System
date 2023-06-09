package com.exercise.irrigation.models;
// Generated May 12, 2023, 10:02:58 AM by Hibernate Tools 6.0.0.Alpha2


import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;


/**
 * TimeSlot generated by hbm2java
 */
@Entity
@Table(name="time_slot"
    ,catalog="irrigation_system"
)
public class TimeSlot  implements java.io.Serializable {


     private Integer id;
     private int startHour;
     private int endHour;
     private Boolean isCompleted;
     private Set<Plot> plots = new HashSet<Plot>(0);

    public TimeSlot() {
    }

	
    public TimeSlot(int startHour, int endHour) {
        this.startHour = startHour;
        this.endHour = endHour;
    }
    public TimeSlot(int startHour, int endHour, Boolean isCompleted, Set<Plot> plots) {
       this.startHour = startHour;
       this.endHour = endHour;
       this.isCompleted = isCompleted;
       this.plots = plots;
    }
   
     @Id @GeneratedValue

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="start_hour", nullable=false)
    public int getStartHour() {
        return this.startHour;
    }
    
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    
    @Column(name="end_hour", nullable=false)
    public int getEndHour() {
        return this.endHour;
    }
    
    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    
    @Column(name="is_completed")
    public Boolean getIsCompleted() {
        return this.isCompleted;
    }
    
    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="timeSlot")
    public Set<Plot> getPlots() {
        return this.plots;
    }
    
    public void setPlots(Set<Plot> plots) {
        this.plots = plots;
    }




}


