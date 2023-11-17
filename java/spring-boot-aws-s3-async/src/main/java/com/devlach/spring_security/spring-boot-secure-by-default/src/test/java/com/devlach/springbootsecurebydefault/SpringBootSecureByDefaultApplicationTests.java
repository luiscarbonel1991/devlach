package com.devlach.springbootsecurebydefault;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class SpringBootSecureByDefaultApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /tasks should return 201 Created")
    @DirtiesContext
        // This annotation is needed to reset the database after each test
    void postTasks() throws Exception {
        mockMvc.perform(post("/tasks")
                        .contentType("application/json")
                        .with(csrf()) // This is needed to pass the CSRF check
                        .content("""
                                {
                                    "description": "Learn Spring Security",
                                    "completed": false,
                                    "owner": "devlach"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn().getResponse().getHeader("Location");
    }

}
