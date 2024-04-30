package org.acme.dto;

import java.util.List;
import java.time.LocalDate;

public record StudentWithCourseDto(Long id,
                String name,
                String major,
                String address,
                String placeOfBirth,
                LocalDate dateOfBirth,
                String hobbies,
                List<StudentCourseDto> studentCourse) {
}