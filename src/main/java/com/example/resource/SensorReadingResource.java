/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

import com.example.model.Room;
import com.example.model.Sensor;
import com.example.model.SensorReading;
import com.example.dao.MockDatabase;
import javax.ws.rs.*;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.example.exception.DataNotFoundException;
import javax.ws.rs.core.Response;
import java.util.UUID;
import com.example.exception.SensorUnavailableException;

/**
 *
 * @author costa
 */
public class SensorReadingResource {
    
    private String sensorId;
    private Map<String, Sensor> sensor = MockDatabase.SENSORS;
    private Map<String, List<SensorReading>> sensorReadings = MockDatabase.SENSOR_READINGS;
    
    public SensorReadingResource(String sensorId){
        this.sensorId = sensorId;
    }
    
    //Get readings
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReadings(){
        if(!sensor.containsKey(sensorId)){
            throw new DataNotFoundException("Sensor with ID " + sensorId + " cannot be found");
        }
        List<SensorReading> readings = sensorReadings.get(sensorId);
        return Response.ok(readings).build();
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReading(SensorReading reading){
        Sensor sensor = this.sensor.get(sensorId);
        if(sensor == null ){
        throw new DataNotFoundException("Sensor with ID " + sensorId + " not found");
        }
    // Checking sensor to ensure it isnt in maintenance
    if (sensor.getStatus().equalsIgnoreCase("MAINTENANCE")){
        throw new SensorUnavailableException("Sensor " + sensorId + " is currently under maintenance");
        
    }
        reading.setId(UUID.randomUUID().toString());
        reading.setTimestamp(System.currentTimeMillis());
        sensorReadings.get(sensorId).add(reading);
        sensor.setCurrentValue(reading.getValue());
        return Response.status(201).entity(reading).build();
    }
    

    }
    
    


    
  