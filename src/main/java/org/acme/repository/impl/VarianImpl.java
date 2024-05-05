package org.acme.repository.impl;


import java.util.List;
import java.util.Optional;

import org.acme.dto.VarianDto;
import org.acme.entity.Varian;
import org.acme.repository.VarianRepository;
import org.acme.response.ApiResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class VarianImpl implements VarianRepository {

    @Inject
    VarianRepository varianRepository;

    // get Varian
    public ApiResponse<VarianDto> getVarianImpl(){
        List<Varian> varians = varianRepository.listAll();
        if (varians.isEmpty()) {
            String message = "tidak ada data";
            boolean successs = false;

            return new ApiResponse<VarianDto>(200, null, message, successs);
        }
    
        List<VarianDto> data = varians.stream()
        .filter(varian -> !varian.isDeleted())
        .map(varian -> new VarianDto(varian.getId(), varian.getTipe()))
        .toList();

        String message =  "berhasil menampilkan data";
        boolean success = true;

        return new ApiResponse<VarianDto>(200, data, message, success);
    }

    // posst / create varian
    @Override
    public Response createVarian(VarianDto req) {
        Varian varian = new Varian();
        
        varian.setTipe(req.tipe());

        Varian.persist(varian);

        return Response.ok().build();
    }

    // put / update varian
    @Override
    public Response editVarianImpl(@PathParam("id") Long id, VarianDto req){
        Optional<Varian> variOptional = varianRepository.findByIdOptional(id);
            Varian varian = variOptional.get();

            varian.setTipe(req.tipe());

            varianRepository.persist(varian);
            return Response.ok().build();
    }

    // delete varian
    public Response deleteVarianImpl(Long id){
        
        Optional<Varian> varianOptional = varianRepository.findByIdOptional(id);
        if (varianOptional.isPresent()) {
            Varian varian = varianOptional.get();
            varian.setDeleted(true);
        }
        return Response.ok().build();

    }


}
