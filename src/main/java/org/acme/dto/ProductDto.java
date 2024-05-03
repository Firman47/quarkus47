package org.acme.dto;

import java.util.List;
public record ProductDto(
    Long id,
    String productname,
    String harga,
    String stok
    // List<VarianDto> varian
 ) {

}
