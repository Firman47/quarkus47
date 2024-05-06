package org.acme.resource;

import org.acme.dto.WarnaDto;
import org.acme.repository.WarnaRepository;
import org.acme.response.ApiResponse;
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

    @GET
    public ApiResponse<WarnaDto> getVarian(){
         return warnaRepository.getWarnaImpl();
    }

    @POST
    @Transactional
    public Response storeVarian(WarnaDto req){
        return warnaRepository.createWarnaImpl(req);
    }


    @PUT
    @Transactional
    @Path("{id}")
    public Response updateVarian(@PathParam("id") Long id, WarnaDto req){
        return warnaRepository.editWarnaImpl(id, req);
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteVarian(@PathParam("id") Long id){
        return  warnaRepository.deleteWarnaImpl(id);
    }

}
