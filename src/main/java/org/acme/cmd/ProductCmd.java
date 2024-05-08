package org.acme.cmd;

public record ProductCmd(
    Long id,
    String productname,
    String harga,
    String stok,
    Long idVarian,
    Long idWarna
   
    
) {

}
