package com.fastcampus.springrunner.divelog.web.diveresort;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fastcampus.springrunner.divelog.IntegrationMockMvcTest;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResortRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@IntegrationMockMvcTest
class DiveResortRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DiveResortRepository diveResortRepository;
    
    List<DiveResort> diveResorts = new ArrayList<>();
    
    @BeforeEach
    void setUp() {
        DiveResort diveResort = DiveResort.create("동해라이프", "허니몬", "033-0000-0000", "동해시 어딘가", "독거남이 운영하는...");
        diveResortRepository.save(diveResort);
        diveResorts.add(diveResort);
        
    }
    
    @AfterEach
    void tearDown() {
        diveResorts.forEach(diveResortRepository::delete);
    }

    @Test
    void test() throws Exception {
        mockMvc.perform(
                    get("/dive-resorts/")
                    .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.[*].id").exists())
                .andExpect(jsonPath("$.[*].name").exists())
                .andExpect(jsonPath("$.[*].address").exists())
                .andExpect(jsonPath("$.[*].ownerName").exists())
                .andExpect(jsonPath("$.[*].contactNumber").exists())
                .andExpect(jsonPath("$.[*].description").exists());
    }
}
