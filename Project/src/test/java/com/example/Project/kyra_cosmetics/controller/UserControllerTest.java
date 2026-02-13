package com.example.Project.kyra_cosmetics.controller;

import com.example.Project.kyra_cosmetics.controller.UserController;
import com.example.Project.kyra_cosmetics.model.User;
import com.example.Project.kyra_cosmetics.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    MockMvc mvc;

    @Mock
    UserService userService;

    @InjectMocks
    com.example.Project.kyra_cosmetics.controller.UserController userController;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void register_returnsUser() throws Exception {
        User in = User.builder().email("a@b.com").name("n").password("p").build();
        User out = User.builder().id(1L).email("a@b.com").name("n").build();

        when(userService.register(any(User.class))).thenReturn(out);

        mvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(in)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").value(1));
    }

    @Test
    void getById_returnsUser() throws Exception {
        when(userService.findById(4L)).thenReturn(User.builder().id(4L).email("x").build());

        mvc.perform(get("/api/users/4")).andExpect(status().isOk()).andExpect(jsonPath(".id").value(4));
    }

    @Test
    void getAll_returnsList() throws Exception {
        when(userService.findAll()).thenReturn(List.of(User.builder().id(2L).build()));

        mvc.perform(get("/api/users")).andExpect(status().isOk()).andExpect(jsonPath("[0].id").value(2));
    }
}
