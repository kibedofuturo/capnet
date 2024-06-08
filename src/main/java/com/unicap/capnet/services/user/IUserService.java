package com.unicap.capnet.services.user;

import com.unicap.capnet.domain.user.ListUserDTO;
import com.unicap.capnet.domain.user.UpdateUserDTO;
import com.unicap.capnet.domain.user.User;
import com.unicap.capnet.domain.user.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    User findById(long userId);

    Page<ListUserDTO> findAllUsers(Pageable pagination);

    void saveUser(UserDTO data);

    void updateUser(UpdateUserDTO data);

    void deleteUser(long userId);

}
