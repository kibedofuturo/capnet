package com.unicap.capnet.domain.user;

import com.unicap.capnet.domain.course.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserDTO(
        @NotBlank
        String name,
        @NotBlank
        String cpf,
        @NotBlank
        String ra,
        boolean isGraduated,
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotNull
        Course course
) {
}
