package com.study.week6.practices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("ControllerAdvice 테스트 - 1")
    public void errorControllerTest() throws Exception {
        mockMvc.perform(get("/errorTest"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Error Test : Product is not found"))
                .andExpect(jsonPath("$.status").value("NOT_FOUND"));
    }

    @Test
    @DisplayName("ControllerAdvice 테스트 - 2")
    public void productsControllerErrorTest() throws Exception {
        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Product id [1] is not found"))
                .andExpect(jsonPath("$.status").value("NOT_FOUND"));
    }

}
