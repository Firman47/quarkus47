package org.acme.repository.impl;

import java.util.List;
import java.util.Optional;

import org.acme.dto.ProductDto;
import org.acme.entity.Product;
import org.acme.repository.ProductRepository;
import org.acme.response.ApiResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;


import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;


@ApplicationScoped
public class ProductImpl implements ProductRepository{
    
    @Inject
    ProductRepository productRepository;

    // get Product
    @Override
    public ApiResponse<ProductDto> getProductImpl(@QueryParam("page") int page, @QueryParam("size") int size){
        PanacheQuery<Product> products = productRepository.findAll();
        products.page(Page.of(page, size));
      
    
        List<ProductDto> data = products.stream()
        .filter(product -> !product.isDeleted())
        .map(product -> new ProductDto(product.getId(), product.getProductName(), product.getHarga(), product.getStok()))
        .toList();

        String message =  "berhasil menampilkan data";
        boolean success = true;
        return new ApiResponse<ProductDto>(200, data, message, success);

    }

    // posst / create product
    @Override
    public Response createProductImpl(ProductDto req){
        Product product = new Product();

        product.setProductName(req.productname());
        product.setHarga(req.harga());
        product.setStok(req.stok());

        productRepository.persist(product);
        return Response.ok().build();
    }

    // put / update product
    @Override
    public Response editProductImpl(@PathParam("id") Long id, ProductDto req){
        Optional<Product> variOptional = productRepository.findByIdOptional(id);
            Product product = variOptional.get();

            product.setProductName(req.productname());
            product.setHarga(req.harga());
            product.setStok(req.stok());

            productRepository.persist(product);
            return Response.ok().build();
    }

    // delete product
    public Response deleteProdductImpl(Long id){
        
        Optional<Product> varianOptional = productRepository.findByIdOptional(id);
        if (varianOptional.isPresent()) {
            Product product = varianOptional.get();
            product.setIsDeleted(true);
        }
        return Response.ok().build();

    }


}
