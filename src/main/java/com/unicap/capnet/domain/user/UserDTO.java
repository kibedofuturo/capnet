package com.unicap.capnet.domain.user;

import com.unicap.capnet.domain.course.Course;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserDTO(
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
