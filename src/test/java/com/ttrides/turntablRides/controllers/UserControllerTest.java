package com.ttrides.turntablRides.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private String correctToken;
    private String unauthorisedToken;

    @BeforeEach
    void setUp() {
        correctToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjFhYWU4ZDdjOTIwNThiNWVlYTQ1Njg5NWJmODkwODQ1NzFlMzA2ZjMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI3NzI2NTQzNDAxODgtOWNvbzkwMm9kOG1pMDdhZmE3Mmo0amtwZjliMTlmbTguYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI3NzI2NTQzNDAxODgtOWNvbzkwMm9kOG1pMDdhZmE3Mmo0amtwZjliMTlmbTguYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTAyMjQ4NzEzMDQxNTAxMDA0NzIiLCJoZCI6InR1cm50YWJsLmlvIiwiZW1haWwiOiJqb2huLmtwYW50ZXlAdHVybnRhYmwuaW8iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6IkdlM0h4NjdOTXA4bDcyRUFtWV9TWmciLCJuYW1lIjoiSm9obiBQYXVsIEtwYW50ZXkiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUdObXl4WWtQUUV2SEFsMncxTlRXUEt6ZXliNVQ2WHBlZVV3NTM4blg0dnY9czk2LWMiLCJnaXZlbl9uYW1lIjoiSm9obiBQYXVsIiwiZmFtaWx5X25hbWUiOiJLcGFudGV5IiwibG9jYWxlIjoiZW4iLCJpYXQiOjE2ODA1OTM1NDcsImV4cCI6MTY4MDU5NzE0N30.I1ahOem_YT_9sxlFdl91tFfR9EOYVJ3Q56klKEMHQUn5JB597vV-kzPSZ86kk2c8AP4XAVuM1s4mb3oEyR3aRsrb8i6NjMqccnFeQWyPFthKPY1mZaj34zrAk4fb6iNs-FQt3PAr4nSnl4d_DtbvaF-Wz7tdUDgS5Gw25hFnrQC5KLH4AOfbCfIQm5pyHMBgEOVoJpwQx8AbINwbvLJcV98RBK_SCGsOWzsxBCYiG-o_i63O9QyM8EcNYXtBXFSbKn0kU6adfaJYZffRgADY3b8WBv75P9TxCdL4Fe6vtPlJWs3KtWLIqevm7ITbUl6qWHv7rCtVoueufWG-iCi6AQ";
        unauthorisedToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjFhYWU4ZDdjOTIwNThiNWVlYTQ1Njg5NWJmODkwODQ1NzFlMzA2ZjMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI3NzI2NTQzNDAxODgtOWNvbzkwMm9kOG1pMDdhZmE3Mmo0amtwZjliMTlmbTguYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI3NzI2NTQzNDAxODgtOWNvbzkwMm9kOG1pMDdhZmE3Mmo0amtwZjliMTlmbTguYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTAyMjQ4NzEzMDQxNTAxMDA0NzIiLCJoZCI6InR1cm50YWJsLmlvIiwiZW1haWwiOiJqb2huLmtwYW50ZXlAdHVybnRhYmwuaW8iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6InhBanZVSS1wblY2dnNLOHM5eFhYU1EiLCJuYW1lIjoiSm9obiBQYXVsIEtwYW50ZXkiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUdObXl4WWtQUUV2SEFsMncxTlRXUEt6ZXliNVQ2WHBlZVV3NTM4blg0dnY9czk2LWMiLCJnaXZlbl9uYW1lIjoiSm9obiBQYXVsIiwiZmFtaWx5X25hbWUiOiJLcGFudGV5IiwibG9jYWxlIjoiZW4iLCJpYXQiOjE2ODA1Mzg0MjksImV4cCI6MTY4MDU0MjAyOX0.XLY2lsIBwjSs6v4nbe5T5CmuBbTpguRb3Hwn1Wm-izYjdROK_a0Ff7EqV4Ztwhg_-BNziF0lIZLbvyeCgOfxT10_OwzDCjrr3PB_1gWqqfCvFMwdvz-OgVuBhg7qyCv5GAJfJCuqu0MCmmWiai_EI68fBG8LTiZf_upVaZAvSJrjNpGQTvGzXK7VpKL4DpTJwVCSQ4aiU94wIDzrPeej0JsOQT0WdtAOpzBz9xY9cwD7QAPCHbcsrIEkdyqKdybRgoXDRnJnJnKjtkC3o9mpFy6cPJeT4n5Mr-LCHSHoRzPezaFOWMsKeHV_ac9w1IVbvGELK1PFGR1oQ9ZHCMsdcQ";

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void loginUser_whenProvidedUnauthorisedOrExpiredOrInvalidToken_ShouldThrow401Exception() throws Exception {
         mockMvc.perform(get("/api/v1/login").header("Authorization", "Bearer " + unauthorisedToken)).andExpect(status().is(401));
    }

    @Test
    void loginUser_WhenProvidedCorrectToken_ShouldBeOkWith200Response() throws Exception{
        MvcResult result = mockMvc.perform(get("/api/v1/login").header("Authorization", "Bearer " + correctToken)).andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals("Secured endpoint", content);
    }
}