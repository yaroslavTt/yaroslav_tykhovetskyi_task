package com.example.yaroslav_tykhovetskyi_tech_task.repository;

import com.example.yaroslav_tykhovetskyi_tech_task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
