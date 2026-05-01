/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;



import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Lenovo
 */
@ApplicationPath("/api/v1")
public class MyApplication extends Application {
    
    @Override
    public Set<Class<?>> getClasses(){
        Set<Class<?>> classes = new HashSet<>();
        classes.add(RoomResource.class);
        classes.add(SensorResource.class);
        classes.add(DiscoverEndPoint.class);
        return classes;
    }
    
    
}
