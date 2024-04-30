package org.acme.dto;

import java.time.LocalDate;

public record StudentDetailDto(Long id,
                Long id_student,
                String address,
                String placeOfBirth,
                LocalDate dateOfBirth,
                String hobbies) {
}