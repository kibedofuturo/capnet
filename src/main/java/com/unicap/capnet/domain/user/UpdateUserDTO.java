package com.unicap.capnet.domain.user;

import com.unicap.capnet.domain.course.Course;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(
        @NotNull
        long id,
        String email,
        String password,
        boolean isGraduated,
        Course course
) {
}
