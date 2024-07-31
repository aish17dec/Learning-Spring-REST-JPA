package com.myrestapi.webservices.restfulwebservices.repository.model;

import com.myrestapi.webservices.restfulwebservices.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserModel extends BaseModel implements UserDetails {

    @Column(name="first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "about")
    private String about;

    @Column(name="birthday")
    private Date birthday;

    @Column(name="email_id", unique = true, nullable = false)
    private String emailId;

    @Column(name="website")
    private String website;

    @Column(name="user_name", unique = true, nullable = false)
    private String userName;

    @Column(name="pass_word")
    private String password;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<PostModel> posts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().name()));
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    public void prePersist(){
        this.setCreatedAt(new Date());
    }
}
