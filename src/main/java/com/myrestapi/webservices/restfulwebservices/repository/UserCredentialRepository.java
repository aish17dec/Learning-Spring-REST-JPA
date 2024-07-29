package com.myrestapi.webservices.restfulwebservices.repository;


import com.myrestapi.webservices.restfulwebservices.controller.UserCredentialsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredentialsDto, String> {
}
