package com.example.kursverwaltung.service;

import com.example.kursverwaltung.domain.UserInfo;
import com.example.kursverwaltung.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoUserDetailsServiceTest {

    @Autowired
    UserInfoUserDetailsService service;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void addUserTest() {
        /*UserInfo user = new UserInfo();
        user.setId(1);
        user.setUsername("ali");
        user.setPassword("1234");
        user.setRoles("ADMIN");
        String userId = service.addUser(user);
        assertNotNull(userId);
        assertEquals("ali", user.getUsername());
        assertNotEquals("1234", user.getPassword());*/
    }

    @Test
    void dataLoader() {
    }

    @Test
    void loadUserByUsername() {
    }
    @Test
    void listAll() {
    }

    @Test
    void save() {
    }

    @Test
    void get() {
    }

    @Test
    void delete() {
    }

    @Test
    void getUserId() {
    }

    @Test
    void getUserList() {
    }

    @Test
    void findByKeyword() {
    }
}