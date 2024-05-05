package org.acme.repository;

import org.acme.dto.VarianDto;
import org.acme.entity.Varian;
import org.acme.response.ApiResponse;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

public interface VarianRepository extends PanacheRepository<Varian> {

  ApiResponse<VarianDto> getVarianImpl();
  Response createVarian(VarianDto varianDto);
  Response editVarianImpl(@PathParam("id") Long id, VarianDto req);
  Response deleteVarianImpl(Long id);

  
}   