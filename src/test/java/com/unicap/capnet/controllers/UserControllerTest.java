package com.unicap.capnet.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicap.capnet.domain.course.Course;
import com.unicap.capnet.domain.user.ListUserDTO;
import com.unicap.capnet.domain.user.User;
import com.unicap.capnet.domain.user.UserDTO;
import com.unicap.capnet.services.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    private UserDTO userDTO;

    @BeforeEach
    void init() {
        user = User.builder().name("João Santos")
                .cpf("12345678910")
                .ra("123456")
                .isGraduated(false)
                .email("joao@gmail.com")
                .password("123456")
                .course(Course.CHEMISTRY)
                .build();

        userDTO = UserDTO.builder().name("João Santos")
                .cpf("12345678910")
                .ra("123456")
                .isGraduated(false)
                .email("joao@gmail.com")
                .password("123456")
                .course(Course.CHEMISTRY)
                .build();
    }

    @Test
    @DisplayName("Cria usuário e retorna ok")
    void register() throws Exception {
        willDoNothing().given(userService).saveUser(any());

        ResultActions response = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)));

        response.andExpect(status().isCreated())
                .andExpect(content().string("Usuário cadastrado com sucesso!"));
    }

    @Test
    @DisplayName("Procura usuário por Id e retorna Ok")
    void getUserByIdCase1() throws Exception {
        User userData = new User(userDTO);

        long id = 1;

        ListUserDTO listUserDTO = new ListUserDTO(userData);

        when(userService.findById(id)).thenReturn(userData);

        ResultActions response = mockMvc.perform(get("/users/{userId}", id));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.cpf").value(user.getCpf()))
                .andExpect(jsonPath("$.ra").value(user.getRa()))
                .andExpect(jsonPath("$.isGraduated").value(user.isGraduated()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.password").value(user.getPassword()))
                .andExpect(jsonPath("$.course").value(user.getCourse().toString()));;
    }

    @Test
    @DisplayName("Procura usuário por Id e retorna Not Found")
    void getUserByIdCase2() throws Exception {
        User userData = new User(userDTO);

        long id = 1;

        ListUserDTO listUserDTO = new ListUserDTO(userData);

        when(userService.findById(2)).thenReturn(userData);

        ResultActions response = mockMvc.perform(get("/users/{userId}", id));

        response.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Mostra todos os usuários e retorna ok")
    void getAllUsers() throws Exception {
        Page<ListUserDTO> userPage = new PageImpl<>(Collections.singletonList(new ListUserDTO(this.user)), PageRequest.of(0, 10), 1);
        when(userService.findAllUsers(any(Pageable.class))).thenReturn(userPage);

        ResultActions response = mockMvc.perform(get("/users"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)));
    }

    @Test
    @DisplayName("Deleta usuario por Id e retorna ok")
    void deleteUserCase1() throws Exception {
        long id = 1;

        when(userService.findById(id)).thenReturn(user);

        doNothing().when(userService).deleteUser(id);

        ResultActions response = mockMvc.perform(delete("/users/{userId}", id));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Usuário excluido com sucesso!"));
    }

    @Test
    @DisplayName("Deleta usuario por Id e retorna not found")
    void deleteUserCase2() throws Exception {
        long id = 1;

        when(userService.findById(2)).thenReturn(user);

        doNothing().when(userService).deleteUser(2);

        ResultActions response = mockMvc.perform(delete("/users/{userId}", id));

        response.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(content().string("Usuário não encontrado."));
    }
}