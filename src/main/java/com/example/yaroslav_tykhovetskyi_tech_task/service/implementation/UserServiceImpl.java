package com.example.yaroslav_tykhovetskyi_tech_task.service.implementation;

import com.example.yaroslav_tykhovetskyi_tech_task.dto.UserResponse;
import com.example.yaroslav_tykhovetskyi_tech_task.exception.UserNotFoundException;
import com.example.yaroslav_tykhovetskyi_tech_task.model.User;
import com.example.yaroslav_tykhovetskyi_tech_task.repository.UserRepository;
import com.example.yaroslav_tykhovetskyi_tech_task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse saveUser(User user) {
        return mapUserToDto(userRepository.save(user));
    }

    @Override
    public UserResponse getUserResponseById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    throw new UserNotFoundException("User not found by id.");
                });

        return mapUserToDto(user);
    }

    private UserResponse mapUserToDto(User user) {
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(calculateAge(user))
                .build();
    }

    private static int calculateAge(User user) {
        return (int)(LocalDate.now().toEpochDay() - user.getDateOfBirth().toEpochDay()) / 365;
    }

    private User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    throw new UserNotFoundException("User not found by id.");
                });
    }

    @Override
    public boolean deleteUserById(Long id) {
        User user = getUserById(id);

        if (Objects.nonNull(user)){
            userRepository.delete(user);
            return true;
        }else{
            throw new UserNotFoundException("User not found by id, can't perform delete operation.");
        }
    }
}
