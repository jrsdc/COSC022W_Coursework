/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

import com.example.model.Room;
import com.example.model.Sensor;
import com.example.model.SensorReading;
import com.example.exception.RoomNotEmptyException;
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

/**
 *
 * @author Lenovo
 */
@Path("/rooms")
public class RoomResource {
    
    private Map<String, Room> rooms = MockDatabase.ROOMS;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Room> getAllRooms(){
        return rooms.values();
    }
    
    
    @GET
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoomById(@PathParam("roomId") String roomId){
        Room room = rooms.get(roomId);
        if (room == null){
            throw new DataNotFoundException("Room with ID "+roomId+" not found");
        }
        return Response.ok(room).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRoom(Room room){
        if (room == null || room.getId().isEmpty()){
            return Response.status(400).entity("Room ID is required").build();
        }
        rooms.put(room.getId(), room);
        return Response.status(201).entity(room).header("Location", "api/v1/rooms" + room.getId()).build();
    }
    
    @DELETE
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRoom(@PathParam("roomId") String roomId){
        
        Room room = rooms.get(roomId);
        
        if (room == null) {
            throw new DataNotFoundException("Room with ID" + roomId+" not found.");
        
        }
        if (!room.getSensorIds().isEmpty()){
            throw new RoomNotEmptyException("The room with ID: "+ roomId+" still has sensors that are active");
        }
        rooms.remove(roomId);
        return Response.status(200).entity("The room " + roomId +" has been deleted").build();
    }
    
    
}
    

