package org.acme.resource;

import org.acme.cmd.VarianCmd;
import org.acme.cmd.WarnaCmd;
import org.acme.dto.ProductWithDto;
import org.acme.dto.VarianDto;
import org.acme.dto.VarianWithDto;
import org.acme.entity.Product;
import org.acme.entity.Varian;
import org.acme.entity.Warna;
import org.acme.repository.ProductRepository;
import org.acme.repository.VarianRepository;
import org.acme.response.ApiResponse;
import org.hibernate.mapping.List;

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

    @Inject
    ProductRepository productRepository;

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



    @POST
    @Path("product")
    @Transactional
    public Response storeVarianProduct(VarianWithDto req){
        Varian varians = new Varian();
        Product product = productRepository.findById(req.product_id());

        varians.setTipe(req.tipe());
        varians.setProduct(product);
        Varian.persist(varians);
        return Response.ok().build();
    }

    // @GET
    // @Path("/product/{id}")
    // @Transactional
    // public ApiResponse<VarianCmd> getVarianProduct(@PathParam("id") Long idProduct)){
    //      Product product = productRepository.findById(idProduct);
    //     if (varian == null) {
    //         return new ApiResponse<VarianCmd>(404, null, "Varian not found", false);
    //     } else {
    //         List<Varian> varians = varianRepository.find("product", product).list();
    //         List<WarnaCmd> data = warnas.stream()
    //                 .map(warna -> new WarnaCmd(warna.getId(), warna.getWarna(), warna.getVarian().getTipe()))
    //                 .toList();
    //         return new ApiResponse<WarnaCmd>(200, data, "success", true);
    //     }
    //     return Response.ok().build();
    // }
}
