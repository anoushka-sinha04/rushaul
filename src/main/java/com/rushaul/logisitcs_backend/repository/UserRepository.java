package com.rushaul.logisitcs_backend.repository;

import com.rushaul.logisitcs_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
