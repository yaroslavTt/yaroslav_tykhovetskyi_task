package com.example.yaroslav_tykhovetskyi_tech_task.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String firstName;
    private String lastName;
    private Integer age;
}