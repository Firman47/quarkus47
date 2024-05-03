package org.acme.dto;

import java.util.List;
public record ProductWithDto(
    Long id,
    String productname,
    String harga,
    String stok,
    List<VarianWithDto> varian
 ) {

}
