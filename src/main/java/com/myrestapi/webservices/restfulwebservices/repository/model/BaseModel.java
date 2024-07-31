package com.myrestapi.webservices.restfulwebservices.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id", nullable = false, updatable = false)
    protected String id;

    @Column(name="created_at", nullable = false, updatable = false)
    @CreatedDate
    protected Date createdAt;

    @Column(name="updated_at")
    @LastModifiedDate
    protected Date updatedAt;
}
