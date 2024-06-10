package com.unicap.capnet.repositories.user;

import com.unicap.capnet.domain.course.Course;
import com.unicap.capnet.domain.user.User;
import com.unicap.capnet.domain.user.UserDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void findAllByActiveTrue() {
    }

    @Test
    @DisplayName("Vai buscar o usuário por Id e retornar verdadeiro")
    void findByIdAndActiveTrueCase1() {
        UserDTO data = new UserDTO(
                "João Santos",
                "12345678901",
                "888888",
                true,
                "joao_test@gmail.com",
                "123456",
                Course.CHEMISTRY
        );

        User user = this.createUser(data);

        Optional<User> result = Optional.ofNullable(this.userRepository.findByIdAndActiveTrue(1));

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Vai buscar o usuário por Id e retornar falso")
    void findByIdAndActiveTrueCase2() {
        Optional<User> result = Optional.ofNullable(this.userRepository.findByIdAndActiveTrue(1));

        assertThat(result.isPresent()).isFalse();
    }

    private User createUser(UserDTO data) {
        User user = new User(data);
        this.entityManager.persist(user);

        return user;
    }
}