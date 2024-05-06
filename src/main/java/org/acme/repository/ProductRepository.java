package org.acme.repository;

import org.acme.dto.ProductDto;
import org.acme.entity.Product;
import org.acme.response.ApiResponse;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

public interface ProductRepository extends PanacheRepository<Product> {

ApiResponse<ProductDto> getProductImpl(@QueryParam("page") int page, @QueryParam("size") int size);
  Response createProductImpl(ProductDto req);
  Response editProductImpl(@PathParam("id") Long id, ProductDto req);
  Response deleteProdductImpl(Long id);


}