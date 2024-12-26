package com.dec24.jobportal.repository;

import com.dec24.jobportal.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {


    Optional<Users> findByEmail(String email);
}
