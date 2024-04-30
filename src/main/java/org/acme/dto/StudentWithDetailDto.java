package org.acme.dto;

import java.util.List;

public record StudentWithDetailDto(Long id,
                String name,
                String major,
                List<StudentDetailDto> studentDetail) {
}