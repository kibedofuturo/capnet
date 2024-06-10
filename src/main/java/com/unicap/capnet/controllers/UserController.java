package com.unicap.capnet.controllers;


import com.unicap.capnet.domain.user.ListUserDTO;
import com.unicap.capnet.domain.user.UpdateUserDTO;
import com.unicap.capnet.domain.user.User;
import com.unicap.capnet.domain.user.UserDTO;
import com.unicap.capnet.services.user.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> register(@RequestBody @Valid UserDTO data) {
        userService.saveUser(data);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ListUserDTO> getUserById(@PathVariable long userId){
        User user = userService.findById(userId);

        if (user != null) {
            ListUserDTO userDTO = new ListUserDTO(user);

            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<ListUserDTO>> getAllUsers(Pageable pagination) {
        Page<ListUserDTO> userPage = userService.findAllUsers(pagination);

        return ResponseEntity.ok(userPage);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateUserDTO data) {
        userService.updateUser(data);
    }

    @DeleteMapping("{userId}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable long userId) {
        User user = userService.findById(userId);

        if (user != null) {
            userService.deleteUser(userId);
            return ResponseEntity.ok("Usuário excluido com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
    }
}
