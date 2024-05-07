package org.acme.resource;

import java.util.List;

import org.acme.cmd.WarnaCmd;
import org.acme.dto.WarnaDto;
import org.acme.dto.WarnaWithDto;
import org.acme.entity.Varian;
import org.acme.entity.Warna;
import org.acme.repository.WarnaRepository;
import org.acme.response.ApiResponse;


import java.util.Map;

import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;



@Path("warna")
@Produces("application/json")
@Consumes("application/json")
public class WarnaResource {
  @Inject
    WarnaRepository warnaRepository;

     // GET WARNA
     @GET
     public ApiResponse<WarnaDto> getVarian(){
          return warnaRepository.getWarnaImpl();
     }

    // GET ALL WARNA VARIAN
    @GET
    @Path("/warnas")
    public ApiResponse<WarnaCmd> getAllWarn(){
       return warnaRepository.getAllWarnaVarianImpl();
    }

    // GET WARNA BY VARIAN
    @GET 
    @Path("/varian/{idVarian}")
    public ApiResponse<WarnaCmd> getWarnaByVarian(Long idVarian) {
       return warnaRepository.getWarnaByVarianImpl(idVarian);
    }
   
    // CREATE WARNA
    @POST
    @Transactional
    public Response soreWarna(WarnaDto req){
        return warnaRepository.createWarnaImpl(req);
    }

    // CREATE WARNA VARIAN
    @POST
    @Path("/varian")
    @Transactional
    public Response createWarnaVarian(WarnaWithDto req){
        Map<String, Object> data = warnaRepository.createWarnaVarianImpl( req);
        return  Response.ok(data).build();
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response updateWarna(@PathParam("id") Long id, WarnaDto req){
        return warnaRepository.editWarnaImpl(id, req);
    }

    // edit warna varian
    @PUT 
    @Path("/varian/{id}")
    @Transactional
    public Response updateWarna(@PathParam("id") Long id, WarnaWithDto req){
        Map<String, Object> data = warnaRepository.editWarnaVarianImpl(id, req);
    return Response.ok(data).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteWarna(@PathParam("id") Long id){
        return  warnaRepository.deleteWarnaImpl(id);
    }



}