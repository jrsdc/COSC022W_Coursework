package com.example.dao;

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

public class MockDatabase {
    public static final Map<String, Room> ROOMS = new HashMap<>();
    public static final Map<String, Sensor> SENSORS  = new HashMap<>();
//  Multiple readings in a room indicate a need for a collection of a collection  
    public static final Map<String, List<SensorReading>> SENSOR_READINGS  = new HashMap<>();

    static {
        // Initialise rooms
       Room room1 = new Room("LIB-001", "Library",50);
       Room room2 = new Room("LAB-001","Lab Room",50);
       ROOMS.put(room1.getId(),room1);
       ROOMS.put(room2.getId(),room2);
   
       

        // Initialise sensors
       Sensor sensor1 = new Sensor("TEMP-01","Temperature","ACTIVE",25,"LIB-001");
       Sensor sensor2 = new Sensor("TEMP-02","Temperature","ACTIVE",21,"LAB-001");
       SENSORS.put(sensor1.getId(), sensor1);
       SENSORS.put(sensor2.getId(), sensor2);
       
        // Linking the rooms to sensors
       room1.getSensorIds().add(sensor1.getId());
       room2.getSensorIds().add(sensor2.getId());

        // Initialise SENSOR_READINGS
        SENSOR_READINGS.put(sensor1.getId(), new ArrayList<>());
        SENSOR_READINGS.put(sensor2.getId(), new ArrayList<>());
        
    }
}