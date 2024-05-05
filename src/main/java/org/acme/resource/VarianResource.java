package org.acme.resource;

import org.acme.dto.VarianDto;
import org.acme.repository.VarianRepository;
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



@Path("varian")
@Produces("application/json")
@Consumes("application/json")
public class VarianResource {
    @Inject
    VarianRepository varianRepository;

    @GET
    public ApiResponse<VarianDto> getVarian(){
       return varianRepository.getVarianImpl();
    }

    @POST
    @Transactional
    public Response storeVarian(VarianDto req){
        return varianRepository.createVarian(req);
    }


    @PUT
    @Transactional
    @Path("{id}")
    public Response updateVarian(@PathParam("id") Long id, VarianDto req){
        return varianRepository.editVarianImpl(id, req);
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteVarian(@PathParam("id") Long id){
        return  varianRepository.deleteVarianImpl(id);
    }

}
