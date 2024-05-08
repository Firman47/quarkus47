package org.acme.dto.pembelian;

import java.util.Date;


public record DetailPembelianDto(
    
    long id,
    String kode_pembelian,
    Long idProduct,
    String namaProduk,
    String harga,

    int jumlah,
    double total,
    double cash,
    double kembalian,
    Date tanggal
) {

}
