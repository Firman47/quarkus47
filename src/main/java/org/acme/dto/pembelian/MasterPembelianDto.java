package org.acme.dto.pembelian;

import java.util.Date;

public record MasterPembelianDto(
     long id,
     Long id_detailPebelian,
    String kode_pembelian,
    int jumlah,
    double total,
    double cash,
    double kembalian,
    Date tanggal
) {

}
