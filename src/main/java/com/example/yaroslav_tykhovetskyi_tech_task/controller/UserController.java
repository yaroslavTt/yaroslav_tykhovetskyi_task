package com.example.yaroslav_tykhovetskyi_tech_task.controller;

import com.example.yaroslav_tykhovetskyi_tech_task.dto.UserResponse;
import com.example.yaroslav_tykhovetskyi_tech_task.model.User;
import com.example.yaroslav_tykhovetskyi_tech_task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getUserResponseById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.saveUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id){
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
