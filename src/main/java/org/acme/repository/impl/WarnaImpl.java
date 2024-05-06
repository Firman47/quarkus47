package org.acme.repository.impl;

import java.util.List;
import java.util.Optional;

import org.acme.dto.WarnaDto;
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
