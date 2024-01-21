package com.fodapi;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class RegisterControllerTest {

    @Test
    public void shouldReturnRegisterViewName() throws Exception {
        RegisterController registerController = new RegisterController();
        MockMvc mockMvc = standaloneSetup(registerController).build();
        mockMvc.perform(get("/register")).andExpect(view().name("register.html"));
    }
}
