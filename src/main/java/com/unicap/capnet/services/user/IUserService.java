package com.unicap.capnet.services.user;

import com.unicap.capnet.domain.user.ListUserDTO;
import com.unicap.capnet.domain.user.UpdateUserDTO;
import com.unicap.capnet.domain.user.User;
import com.unicap.capnet.domain.user.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserService {
    User findById(long userId);

    Page<ListUserDTO> findAllUsers(Pageable pagination);

    void saveUser(UserDTO data);

    Optional<User> updateUser(UpdateUserDTO data, Long userId);

    void deleteUser(long userId);
}
