/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.exception;

import com.example.model.ErrorMessage;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 *
 * @author costa
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{
            
        @Override
        public Response toResponse(Throwable exception){
            ErrorMessage error = new ErrorMessage("An internal server errorhas occured.", 500, "This document is not available at the moment.");
        
               return Response.status(500).entity(error).build();
        
        }
}
    

