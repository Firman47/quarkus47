package org.acme.repository;

import org.acme.dto.WarnaDto;
import org.acme.entity.Warna;
import org.acme.response.ApiResponse;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

public interface WarnaRepository extends PanacheRepository<Warna> {

ApiResponse<WarnaDto> getWarnaImpl();
  Response createWarnaImpl(WarnaDto req);
  Response editWarnaImpl(@PathParam("id") Long id, WarnaDto req);
  Response deleteWarnaImpl(Long id);
}