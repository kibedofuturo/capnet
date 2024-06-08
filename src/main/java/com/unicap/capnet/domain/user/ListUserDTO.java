package com.unicap.capnet.domain.user;

import com.unicap.capnet.domain.course.Course;

public record ListUserDTO(
        long id,
        String name,
        String cpf,
        String ra,
        Boolean isGraduated,
        String email,
        String password,
        Course course
) {
    public ListUserDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getCpf(),
                user.getRa(),
                user.isGraduated(),
                user.getEmail(),
                user.getPassword(),
                user.getCourse()
        );
    }
}
