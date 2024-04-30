package org.acme.dto;

import java.time.LocalDate;

public record StudentDto(
        Long id,
        String name,
        String major,
          String address,
        String placeOfBirth,
        LocalDate dateOfBirth,
        String hobbies) {

}