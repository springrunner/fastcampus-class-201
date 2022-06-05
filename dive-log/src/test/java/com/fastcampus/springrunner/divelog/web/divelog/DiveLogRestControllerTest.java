package com.fastcampus.springrunner.divelog.web.divelog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.fastcampus.springrunner.divelog.IntegrationMockMvcTest;
import com.fasterxml.jackson.databind.ObjectMapper;

@IntegrationMockMvcTest
public class DiveLogRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

}
