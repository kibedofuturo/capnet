package com.unicap.capnet.domain.user;

import com.unicap.capnet.domain.course.Course;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotNull
        long id,
        @NotNull
        String name,
        @NotNull
        String cpf,
        @NotNull
        String ra,
        boolean isGraduated,
        String email,
        String password,
        Course course
) {
}
