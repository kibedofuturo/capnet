package com.unicap.capnet.services.user;

import com.unicap.capnet.domain.user.ListUserDTO;
import com.unicap.capnet.domain.user.UpdateUserDTO;
import com.unicap.capnet.domain.user.User;
import com.unicap.capnet.domain.user.UserDTO;
import com.unicap.capnet.repositories.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(long userId) {
        return userRepository.findByIdAndActiveTrue(userId);
    }

    @Override
    public Page<ListUserDTO> findAllUsers(Pageable pagination) {
        return userRepository.findAllByActiveTrue(pagination).map(ListUserDTO::new);
    }

    @Override
    public void saveUser(UserDTO data) {
        userRepository.save(new User(data));
    }

    @Override
    public void updateUser(UpdateUserDTO data) {
        var user = userRepository.getReferenceById(data.id());
        user.updateInfo(data);
    }

    @Override
    public void deleteUser(long userId) {
        var user = userRepository.getReferenceById(userId);
        user.delete();
    }
}
