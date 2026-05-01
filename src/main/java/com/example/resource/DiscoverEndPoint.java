/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Lenovo
 */
@Path("/")
public class DiscoverEndPoint {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApiInfo(){
        Map<String, Object> info = new HashMap<>();
        info.put("v", "1.0");
        info.put("name", "Smart Campus");
        info.put("contact", "info@smartcampus.com");
        
        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "/api/v1/rooms");
        resources.put("sensors", "/api/v1/sensors");
        
        info.put("resources", resources);
        
        return Response.ok(info).build();
    }
    
}
