package org.acme.dto;

import java.util.List;

public record ProductWitVarianDto(
     Long id,
    String productname,
    String harga,
    String stok,
    Long idvarian,
    Long idWarna,
    String tipe,
    String warna
) {

}
