package org.acme.repository;

import org.acme.cmd.WarnaCmd;
import org.acme.dto.WarnaDto;
import org.acme.dto.WarnaWithDto;
import org.acme.entity.Warna;
import org.acme.response.ApiResponse;
import java.util.Map;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

public interface WarnaRepository extends PanacheRepository<Warna> {
  // get all warna
  ApiResponse<WarnaDto> getWarnaImpl();

  // get all warna varian
  ApiResponse<WarnaCmd> getAllWarnaVarianImpl();

  // // // get by varian id
  ApiResponse<WarnaCmd> getWarnaByVarianImpl(Long idVarian);

  // EDIT WARNA VARIAN
  public Map<String, Object> editWarnaVarianImpl(@PathParam("id") Long id, WarnaWithDto req);

  Map<String, Object>createWarnaVarianImpl(WarnaWithDto req);
  Response createWarnaImpl(WarnaDto req);
  Response editWarnaImpl(@PathParam("id") Long id, WarnaDto req);
  Response deleteWarnaImpl(Long id);


}