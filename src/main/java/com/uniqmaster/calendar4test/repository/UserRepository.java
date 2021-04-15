package com.uniqmaster.calendar4test.repository;

import com.uniqmaster.calendar4test.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String userName);

    UserDetails findByEmail(String email);
}
