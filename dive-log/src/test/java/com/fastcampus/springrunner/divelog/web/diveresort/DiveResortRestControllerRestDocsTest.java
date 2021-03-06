package com.fastcampus.springrunner.divelog.web.diveresort;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fastcampus.springrunner.divelog.IntegrationMockMvcAndRestDocsTest;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResortRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@IntegrationMockMvcAndRestDocsTest
class DiveResortRestControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DiveResortRepository diveResortRepository;

    private DiveResort diveResort;

    @BeforeEach
    void setUp() {
        diveResort = DiveResort.create("???????????????2", "?????????", "033-0000-0000", "????????? ?????????", "???????????? ????????????...");
        diveResortRepository.save(diveResort);
    }

    @AfterEach
    void tearDown() {
        diveResortRepository.deleteAll();
    }

    @Test
    void testGetDiverResorts() throws Exception {
        mockMvc.perform(get("/dive-resorts/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("dive-resorts-get-list",
                        Preprocessors.preprocessRequest(prettyPrint()),
                        Preprocessors.preprocessResponse(prettyPrint()),
                        responseFields(fieldWithPath("[].id").description("DiveResortId"),
                                fieldWithPath("[].name").description("????????? ??????"),
                                fieldWithPath("[].address").description("????????? ??????"),
                                fieldWithPath("[].ownerName").description("????????????????????????"),
                                fieldWithPath("[].contactNumber").description("????????? ?????????"),
                                fieldWithPath("[].description").description("????????? ??????"),
                                fieldWithPath("[].createdDateTime").description("????????????"),
                                fieldWithPath("[].lastModifiedDateTime").description("??????????????????"))));
    }

    @Test
    void testRegister() throws JsonProcessingException, Exception {
        String requestContent = "{\"name\": \"?????????????????????\", \"address\":\"????????? ?????????\",\"ownerName\":\"?????????\","
                + "\"contactNumber\":\"033-0000-0000\",\"description\":\"????????? ??????!!\"}";


        mockMvc.perform(post("/dive-resorts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(document("dive-resorts-register",
                        Preprocessors.preprocessRequest(prettyPrint()),
                        Preprocessors.preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("name").description("????????? ??????"),
                                fieldWithPath("address").description("????????? ??????"),
                                fieldWithPath("ownerName").description("????????????????????????"),
                                fieldWithPath("contactNumber").description("????????? ?????????"),
                                fieldWithPath("description").description("????????? ??????")),
                        responseFields(fieldWithPath("id").description("DiveResortId"),
                                fieldWithPath("name").description("????????? ??????"),
                                fieldWithPath("address").description("????????? ??????"),
                                fieldWithPath("ownerName").description("????????????????????????"),
                                fieldWithPath("contactNumber").description("????????? ?????????"),
                                fieldWithPath("description").description("????????? ??????"),
                                fieldWithPath("createdDateTime").description("????????????"),
                                fieldWithPath("lastModifiedDateTime").description("??????????????????"))));
    }

    @Test
    void testFindById() throws Exception {
        mockMvc.perform(get("/dive-resorts/{diveResortId}", diveResort.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("dive-resorts-find-by-id",
                        Preprocessors.preprocessRequest(prettyPrint()),
                        Preprocessors.preprocessResponse(prettyPrint()),
                        RequestDocumentation.pathParameters(
                                parameterWithName("diveResortId").description("DiveResort ID")),
                        PayloadDocumentation.responseFields(
                                fieldWithPath("id").description("DiveResortId"),
                                fieldWithPath("name").description("????????? ??????"),
                                fieldWithPath("address").description("????????? ??????"),
                                fieldWithPath("ownerName").description("????????????????????????"),
                                fieldWithPath("contactNumber").description("????????? ?????????"),
                                fieldWithPath("description").description("????????? ??????"),
                                fieldWithPath("createdDateTime").description("????????????"),
                                fieldWithPath("lastModifiedDateTime").description("??????????????????"))));
    }

    @Test
    void testUpdate() throws JsonProcessingException, Exception {
        String requestContent = "{\"name\": \"?????????????????????2\", \"address\":\"????????? ?????????2\",\"ownerName\":\"?????????2\","
                + "\"contactNumber\":\"033-0000-0002\",\"description\":\"????????? ??????!!!!\"}";


        mockMvc.perform(put("/dive-resorts/{diveResortId}", diveResort.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("dive-resorts-update",
                        Preprocessors.preprocessRequest(prettyPrint()),
                        Preprocessors.preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("diveResortId").description("??????????????? DiveResort ID")),
                        requestFields(
                                fieldWithPath("name").description("????????? ??????"),
                                fieldWithPath("address").description("????????? ??????"),
                                fieldWithPath("ownerName").description("????????????????????????"),
                                fieldWithPath("contactNumber").description("????????? ?????????"),
                                fieldWithPath("description").description("????????? ??????")),
                        responseFields(fieldWithPath("id").description("DiveResortId"),
                                fieldWithPath("name").description("????????? ??????"),
                                fieldWithPath("address").description("????????? ??????"),
                                fieldWithPath("ownerName").description("????????????????????????"),
                                fieldWithPath("contactNumber").description("????????? ?????????"),
                                fieldWithPath("description").description("????????? ??????"),
                                fieldWithPath("createdDateTime").description("????????????"),
                                fieldWithPath("lastModifiedDateTime").description("??????????????????"))));
    }

    @Test
    void testDelete() throws JsonProcessingException, Exception {
        mockMvc.perform(delete("/dive-resorts/{diveResortId}", diveResort.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(document("dive-resorts-delete",
                        Preprocessors.preprocessRequest(prettyPrint()),
                        Preprocessors.preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("diveResortId").description("??????????????? DiveResort ID"))
                ));
    }
}
