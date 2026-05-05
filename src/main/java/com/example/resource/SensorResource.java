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
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.example.exception.DataNotFoundException;
import javax.ws.rs.core.Response;
import com.example.exception.LinkedResourceNotFoundException;
/**
 *
 * @author Lenovo
 */
@Path("/sensors")
public class SensorResource {
    
    private Map<String, Sensor> sensors = MockDatabase.SENSORS;
    private Map<String, Room> rooms = MockDatabase.ROOMS;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Sensor> getAllSensors(@QueryParam("type") String sensorType) {
        
        if ((sensorType == null || sensorType.isEmpty() || sensorType.isBlank())){
            return sensors.values();
        }
        List<Sensor>filteredSensors = new ArrayList<>();
        for(Sensor sensor: sensors.values()){
            if (sensor.getType().equalsIgnoreCase(sensorType)){
                filteredSensors.add(sensor);
            }
        }
        
        return filteredSensors;
    }
    
    @GET
    @Path("/{sensorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensorById(@PathParam("sensorId") String sensorId){
        Sensor sensor = sensors.get(sensorId);
        if (sensor == null){
            throw new DataNotFoundException("Sensor with ID " + sensorId + " not found");
        }
        return Response.ok(sensor).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSensor(Sensor sensor){
        if (sensor == null || sensor.getId().isEmpty() || sensor.getId() == null){
            return Response.status(400).entity("sensor ID is required").build();
        }
        if (sensor.getRoomId() == null || !rooms.containsKey(sensor.getRoomId())){
            throw new LinkedResourceNotFoundException("Room with ID " + sensor.getRoomId() + " doesn't exist");
            }
        
        // Adding sensors
        rooms.get(sensor.getRoomId()).getSensorIds().add(sensor.getId());
        
        sensors.put(sensor.getId(), sensor);
        return Response.status(201).entity(sensor).build();
        
    }
        
    // add the sub resource
    @Path("/{sensorId}/readings")
    public SensorReadingResource getReadResource(@PathParam("sensorId") String sensorId){
        return new SensorReadingResource(sensorId);
        }
    }
    

 