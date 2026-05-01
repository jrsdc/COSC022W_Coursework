/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.exception;

import com.example.model.ErrorMessage;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


/**
 *
 * @author costa
 */
public class LinkedResourceNotFoundExceptionMapper  implements ExceptionMapper<LinkedResourceNotFoundException>{
            
        @Override
        public Response toResponse(LinkedResourceNotFoundException exception){
            ErrorMessage error = new ErrorMessage(exception.getMessage(), 409, "This document is not available at the moment");
        
               return Response.status(409).entity(error).build();
        
        }
}