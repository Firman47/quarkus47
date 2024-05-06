package org.acme.dto;

import java.util.List;
public record VarianWithDto(
    Long id,
    Long product_id,
    String tipe,
    List<WarnaWithDto> warna
) {

}
