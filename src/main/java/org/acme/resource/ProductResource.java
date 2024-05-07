package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.persistence.TypedQuery;
// import jakarta.ws.rs.core.Variant;

// import org.acme.response.ApiresResponse;
// import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.acme.repository.ProductRepository;
import org.acme.response.ApiResponse;
import org.acme.entity.Product;
import org.acme.cmd.ProductCmd;
// import org.acme.entity.Warna;
// import org.acme.entity.Varian;
import org.acme.dto.ProductDto;
import org.acme.dto.ProductWitVarianDto;
// import org.acme.dto.VarianDto;
// import org.acme.dto.WarnaWithDto;
import org.acme.dto.ProductWithDto;
import org.acme.dto.VarianWithDto;
import org.acme.dto.WarnaWithDto;

@Path("products")
@Produces("application/json")
@Consumes("application/json")
public class ProductResource {
    @Inject
    ProductRepository productRepository; 

    // get all product
    @GET
    @Path("getProduct")
    public ApiResponse<ProductWitVarianDto> getStudentsWithDetail() {
        List<Product> products = productRepository.listAll();
            
        List<ProductWitVarianDto> data = products.stream().map(product -> {
            List<VarianWithDto> productVarians = product.getVarians().stream()
                    .map(varian -> {
                        List<WarnaWithDto> warnas = varian.getWarna().stream().map(warna -> {
                            return new WarnaWithDto(
                                warna.getId(), 
                                 warna.getWarna(), 
                                 warna.getVarian().getId());
                        }).toList();
                        return new VarianWithDto(varian.getId(),
                                varian.getProduct().getId(),
                                varian.getTipe(), warnas);
                    })
                    .toList();
            return new ProductWitVarianDto(product.getId(), product.getProductName(), product.getHarga(), product.getStok(), product.getVarian().getId(), product.getWarna().getId(), product.getVarian().getTipe(), product.getWarna().getWarna());
        }).toList();
        String message = "success";
        boolean success = true;
        return new ApiResponse<ProductWitVarianDto>(200, data, message, success);
    }

    // get edit product by id
    @GET
    @Path("/{id}")
    public Response getStudent(@PathParam("id") Long id) {
        Map<String, Object> map = new HashMap<>();
        Optional<Product> productOptional = productRepository.findByIdOptional(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            ProductDto data = new ProductDto(product.getId(), product.getProductName(), product.getHarga(),
                    product.getStok());
            String message = "success";
            boolean success = true;
            map.put("data", data);
            map.put("message", message);
            map.put("success", success);
            return Response.ok(map).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    
    @GET
    public ApiResponse<ProductDto> getProduct(@QueryParam("page") int page, @QueryParam("size") int size){
         return productRepository.getProductImpl(page, size);
    }

    @POST
    @Transactional
    public Response createProduct(ProductDto req){
        return productRepository.createProductImpl(req);
    }


    @PUT
    @Transactional
    @Path("{id}")
    public Response updateVarian(@PathParam("id") Long id, ProductDto req){
        return productRepository.editProductImpl(id, req);
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteVarian(@PathParam("id") Long id){
        return  productRepository.deleteProdductImpl(id);
    }

    @POST 
    @Path("products")
    @Transactional
    public Response createProducts(ProductCmd req){
        return productRepository.createProductsImpl(req);
    }

}

