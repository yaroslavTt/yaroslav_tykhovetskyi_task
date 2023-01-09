package com.example.yaroslav_tykhovetskyi_tech_task.service;

import com.example.yaroslav_tykhovetskyi_tech_task.dto.UserResponse;
import com.example.yaroslav_tykhovetskyi_tech_task.model.User;

public interface UserService {

    UserResponse saveUser(User user);

    UserResponse getUserResponseById(Long id);

    boolean deleteUserById(Long id);
}