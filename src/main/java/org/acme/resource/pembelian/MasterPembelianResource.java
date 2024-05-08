package org.acme.resource.pembelian;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import org.acme.entity.pembelian.DetailPembelian;
import org.acme.entity.pembelian.MasterPembelian;
import org.acme.response.ApiResponse;
import org.jboss.logging.annotations.Pos;

import io.quarkus.narayana.jta.runtime.TransactionConfiguration;

import org.acme.dto.pembelian.MasterPembelianDto;;
@Path("pembelian")
@Produces("application/json")
@Consumes("application/json")

public class MasterPembelianResource {

    @GET
    public ApiResponse<MasterPembelianDto> getMasterPembelian(){
        List<MasterPembelian> masterPemblianList = MasterPembelian.listAll();
        List<MasterPembelianDto> data = masterPemblianList.stream()
        .map(pembelian -> new MasterPembelianDto(
        pembelian.getId(),
        pembelian.getDetailPembelian().getId(),
         pembelian.getKode_pembelian(),
          pembelian.getJumlah(),
           pembelian.getTotal(),
            pembelian.getCash(),
            pembelian.getKembalian(),
            pembelian.getTanggal()
            )).toList();
        return new ApiResponse<MasterPembelianDto>(200, data, "berhasil menampilkan data", true);
        }

        @POST
        @Transactional
        public  ApiResponse<MasterPembelianDto> createMasterResponse(MasterPembelianDto req){
            MasterPembelian pembelians = new MasterPembelian();
            DetailPembelian detPembelian = DetailPembelian.findById(req.id_detailPebelian());
            if (pembelians == null) {
                return new ApiResponse<MasterPembelianDto>(404, null ,"tidak berhaisl menyimpan data", false);
            }
            pembelians.setKode_pembelian(req.kode_pembelian());

            pembelians.setDetailPembelian(detPembelian);
            pembelians.setJumlah(detPembelian.getJumlah());
            pembelians.setTotal(detPembelian.getTotal());
            pembelians.setCash(detPembelian.getCash());
            pembelians.setKembalian(detPembelian.getKembalian());
            pembelians.setTanggal(detPembelian.getTanggal());;

            MasterPembelian.persist(pembelians);
            // return Response.ok("data berhasil masuk").build();

        return new ApiResponse<MasterPembelianDto>(200, null, "berhasil menambahkan data", true );

        }
}