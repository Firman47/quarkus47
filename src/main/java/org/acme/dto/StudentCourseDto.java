package org.acme.dto;

public record StudentCourseDto(
    Long id,
    long id_student,
    String coursename,
    String day,
    String time
) {

}
