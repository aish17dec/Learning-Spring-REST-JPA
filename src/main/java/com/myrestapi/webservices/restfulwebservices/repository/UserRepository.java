package com.myrestapi.webservices.restfulwebservices.repository;

import com.myrestapi.webservices.restfulwebservices.repository.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {
    Optional<UserModel> findByUserName(String username);

    void deleteByUserName(String userName);
}
