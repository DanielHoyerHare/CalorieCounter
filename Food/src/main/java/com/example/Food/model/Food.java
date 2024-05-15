package com.example.Food.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Food implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
    //@Column(nullable = false)
    String name;
    
    //@Column(columnDefinition = "FLOAT")
    public Float energy;
    
    //@Column(columnDefinition = "FLOAT")
    public Float fat;
    
    //@Column(columnDefinition = "FLOAT")
    public Float carbohydrate;
    
    //@Column(columnDefinition = "FLOAT")
    public Float protein;
    
    //@Column(columnDefinition = "FLOAT")
    public Float salt;
}
