package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.repository.UserInfoRepository;
import com.example.kursverwaltung.service.UserInfoUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.kursverwaltung.domain.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@WebMvcTest(controllers = UserInfoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserInfoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private  UserInfoUserDetailsService userInfoService;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testAddNewUser() throws Exception{

    }




    @Test
    public void testSaveUser() throws Exception {
/*        // create a test user object
        UserInfo user = new UserInfo();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setRoles("ADMIN");

        // perform a POST request to the /saveUser endpoint with the test user object as the request body
        mvc.perform(MockMvcRequestBuilders.post("/saveUser")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", user.getUsername())
                        .param("password", user.getPassword())
                        .param("roles", user.getRoles()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/users"));

        // verify that the user was added to the database by checking the ServiceInfoUser object
        List<UserInfo> userList = userInfoService.getUserList();
        assertEquals(1, userList.size());
        UserInfo savedUser = userList.get(0);
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getPassword(), savedUser.getPassword());
        assertEquals(user.getRoles(), savedUser.getRoles());*/
    }


    @Test
    void viewHomePageUser() {
    }

    @Test
    void deleteUser() {
    }
}