package org.acme.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import org.acme.cmd.WarnaCmd;
import org.acme.dto.VarianDto;
import org.acme.dto.WarnaDto;
import org.acme.dto.WarnaWithDto;
import org.acme.entity.Varian;
import org.acme.entity.Warna;
import org.acme.repository.WarnaRepository;
import org.acme.response.ApiResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;


@ApplicationScoped

public class  WarnaImpl implements WarnaRepository{
   
    @Inject
    WarnaRepository warnaRepository;

    // get all warna varian 
    @Override 
    public ApiResponse<WarnaCmd> getAllWarnaVarianImpl(){
        List<Warna> warnas = warnaRepository.listAll();
        List<WarnaCmd> data = warnas.stream()
            .map(warnna -> new WarnaCmd(warnna.getId(), warnna.getWarna(), warnna.getVarian().getTipe()))
            .toList();
        return new ApiResponse<WarnaCmd>(200, data, "berhasil menampilkandata" , false);
    }

    // get warna by id varian
    public ApiResponse<WarnaCmd> getWarnaByVarianImpl(Long idVarian){
        Varian varian = Varian.findById(idVarian);
        if (varian == null) {
            return new ApiResponse<WarnaCmd>(404, null, "Varian not found", false);
        } else {
            List<Warna> warnas = warnaRepository.find("varian", varian).list();
            List<WarnaCmd> data = warnas.stream()
                    .map(warna -> new WarnaCmd(warna.getId(), warna.getWarna(), warna.getVarian().getTipe()))
                    .toList();
            return new ApiResponse<WarnaCmd>(200, data, "success", true);
        }
    }

    // create warna id varian 
    @Override
    public Map<String, Object> createWarnaVarianImpl(WarnaWithDto req) {
        Warna warna = new Warna();
        warna.setWarna(req.warna());
        Varian varians = Varian.findById(req.idVarian());
        if (varians == null) {
            return Map.of("error", "Varian not found");
        } else {
            warna.setVarian(varians);
            warnaRepository.persist(warna);
            return Map.of("id", warna.getId(), "name", warna.getWarna(), "varianName", warna.getVarian().getTipe());
        }

    }

    // get Warna
    @Override
    public ApiResponse<WarnaDto> getWarnaImpl(){
        List<Warna> warnas = warnaRepository.listAll();
        if (warnas.isEmpty()) {
            String message = "tidak ada data";
            boolean successs = false;

            return new ApiResponse<WarnaDto>(200, null, message, successs);
        }
    
        List<WarnaDto> data = warnas.stream()
        .filter(warna -> !warna.isDeleted())
        .map(warna -> new WarnaDto(warna.getId(), warna.getWarna()))
        .toList();

        String message =  "berhasil menampilkan data";
        boolean success = true;

        return new ApiResponse<WarnaDto>(200, data, message, success);
    }

    // posst / create warna
    @Override
    public Response createWarnaImpl(WarnaDto req){
        Warna warna = new Warna();

        warna.setWarna(req.warna());
       
        warnaRepository.persist(warna);
        return Response.ok().build();
    }

    // put / update warna
    @Override
    public Response editWarnaImpl(@PathParam("id") Long id, WarnaDto req){
        Optional<Warna> variOptional = warnaRepository.findByIdOptional(id);
            Warna warna = variOptional.get();
            warna.setWarna(req.warna());
            warnaRepository.persist(warna);
            return Response.ok().build();
    }

    @Override 
    public Map<String, Object> editWarnaVarianImpl(@PathParam("id") Long id, WarnaWithDto req){
        Optional<Warna> warnaOptional = warnaRepository.findByIdOptional(id);

        Warna warna = warnaOptional.get();
        Varian varian = Varian.findById(req.idVarian());

        if (varian == null) {
            return Map.of("error", "Varian tidak ada");
        }
        
        warna.setWarna(req.warna());
        warna.setVarian(varian);
       
        warnaRepository.persist(warna);
        return Map.of("id", warna.getId(), "name", warna.getWarna(), "varianName", warna.getVarian().getTipe());

    }

    // delete warna
    public Response deleteWarnaImpl(Long id){
        
        Optional<Warna> varianOptional = warnaRepository.findByIdOptional(id);
        if (varianOptional.isPresent()) {
            Warna warna = varianOptional.get();
            warna.setDeleted(true);
        }
        return Response.ok().build();

    }


}
