package org.acme.resource.pembelian;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.acme.dto.pembelian.DetailPembelianDto;
import org.acme.dto.pembelian.MasterPembelianDto;
import org.acme.entity.Product;
import org.acme.entity.pembelian.DetailPembelian;
import org.acme.response.ApiResponse;

@Path("detailpembelian")
@Produces("application/json")
@Consumes("application/json")
public class DetailPembelianResource {

    @GET

    public Response getDetailPembelian(){
        List<DetailPembelian> detailPembelians  = DetailPembelian.listAll();

        List<DetailPembelianDto> data = detailPembelians.stream()
        .map(dp  -> new DetailPembelianDto(dp.getId(), 
        dp.getKode_pembelian(), 
        dp.getProduct().getId(),
        dp.getProduct().getProductName(),
        dp.getProduct().getStok(),
         dp.getJumlah(), 
         dp.getTotal(),
         dp.getCash(), 
         dp.getKembalian(),
         dp.getTanggal()
      )).toList();
        return Response.ok(data).build();

    }

    @POST
    @Transactional
    public ApiResponse<DetailPembelianDto> createDetailPembelian(DetailPembelianDto req){
        Product product =  Product.findById(req.idProduct());
        DetailPembelian detailPembelian = new DetailPembelian();

        if (req == null || req.idProduct() == null || req.jumlah() <= 0 || req.total() <= 0 || req.cash() <= 0 || req.kembalian() < 0) {
            // Jika data tidak valid atau kosong, kembalikan respons dengan kode 400 Bad Request
            return new ApiResponse<>(400, null, "Gagal menambahkan data", false);
        }else{
            detailPembelian.getNomorTransaksi();

        detailPembelian.setNamaProduk(product.getProductName());
        detailPembelian.setHargaProduk(product.getHarga());
        detailPembelian.setProduct(product);
        detailPembelian.setJumlah(req.jumlah());
        detailPembelian.setTotal(req.total());

        detailPembelian.setCash(req.cash());
        detailPembelian.setKembalian(req.kembalian());

        DetailPembelian.persist(detailPembelian);
        
        // return ponse.ok("data berhasil masuk").build();

        return new ApiResponse<DetailPembelianDto>(200, null, "Berhasil menambahkan data", true );

        }

        
        }
    }

 