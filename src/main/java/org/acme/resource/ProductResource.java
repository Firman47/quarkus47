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
import net.bytebuddy.asm.Advice.Return;

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
import org.acme.repository.VarianRepository;
import org.acme.repository.WarnaRepository;
import org.acme.response.ApiResponse;
import org.acme.entity.Product;
// import org.acme.entity.Warna;
// import org.acme.entity.Varian;
import org.acme.dto.ProductDto;
// import org.acme.dto.VarianDto;
// import org.acme.dto.WarnaDto;
import org.acme.dto.ProductWithDto;
import org.acme.dto.VarianWithDto;
import org.acme.dto.WarnaDto;

@Path("products")
public class ProductResource {
    @Inject
    ProductRepository productRepository; 

    @Inject
    VarianRepository varianRepository; 

    @Inject
    WarnaRepository warnaRepository; 

    // get products
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public ApiResponse<ProductDto> getProducts() {
        List<Product> products = productRepository.listAll();

        List<ProductDto> data = products.stream().map(product -> new ProductDto(
                        product.getId(), 
                        product.getProductName(),
                        product.getHarga(),
                        product.getStok())).toList();
        String message = "success";
        boolean success = true;
        return new ApiResponse<ProductDto>(200, data, message, success);
    }

    // get all product
    @GET
    @Path("product")
    @Produces("application/json")
    @Consumes("application/json")
    public ApiResponse<ProductWithDto> getStudentsWithDetail() {
        List<Product> products = productRepository.listAll();
            
        List<ProductWithDto> data = products.stream().map(product -> {
            List<VarianWithDto> productVarians = product.getVarian().stream()
                    .map(varian -> {
                        List<WarnaDto> warnas = varian.getWarna().stream().map(warna -> {
                            return new WarnaDto(warna.getId(),  warna.getWarna(), warna.getVarian().getId());
                        }).toList();
                        return new VarianWithDto(varian.getId(),
                                varian.getProduct().getId(),
                                varian.getTipe(), warnas);
                    })
                    .toList();
            return new ProductWithDto(product.getId(), product.getProductName(), product.getHarga(), product.getStok(),
            productVarians);
        }).toList();
        String message = "success";
        boolean success = true;
        return new ApiResponse<ProductWithDto>(200, data, message, success);
    }

    // get edit product by id
    @GET
    @Path("/{id}")
    @Produces("application/json")
    @Consumes("application/json")
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

    // create product
    @POST
    @Transactional
    @Produces("application/json")
    @Consumes("application/json")
    public Response storeProduct(ProductDto req){
        Product product = new Product();

        product.setProductName(req.productname());
        product.setHarga(req.harga());
        product.setStok(req.stok());
        productRepository.persist(product);
        return Response.ok().build();
    }
     
    // update product
    @PUT
    @Path("/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response updateProducts(@PathParam("id") Long id, ProductDto req){
        Optional<Product> productOptional = productRepository.findByIdOptional(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setProductName(req.productname());
            product.setHarga(req.harga());
            product.setStok(req.stok());

            productRepository.persist(product);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // // delete product
    // @DELETE
    // @Path("/{id}")
    // @Produces("application/json")
    // @Consumes("application/json")
    // public Response deleteProduct(@PathParam("id") Long id){
    // Optional<Product> productOptional = productRepository.findByIdOptional(id);

    // if (productOptional.isPresent()) {
    //     productOptional.is
    // }


    // return Response.ok().build();
    // }


}

