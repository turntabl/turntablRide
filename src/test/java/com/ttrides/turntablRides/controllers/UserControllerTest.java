package com.ttrides.turntablRides.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttrides.turntablRides.models.response.TTResponse;
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
        // Must update to a fresh token to test
        correctToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6Ijk2OTcxODA4Nzk2ODI5YTk3MmU3OWE5ZDFhOWZmZjExY2Q2MWIxZTMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI3NzI2NTQzNDAxODgtOWNvbzkwMm9kOG1pMDdhZmE3Mmo0amtwZjliMTlmbTguYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI3NzI2NTQzNDAxODgtOWNvbzkwMm9kOG1pMDdhZmE3Mmo0amtwZjliMTlmbTguYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTAyMjQ4NzEzMDQxNTAxMDA0NzIiLCJoZCI6InR1cm50YWJsLmlvIiwiZW1haWwiOiJqb2huLmtwYW50ZXlAdHVybnRhYmwuaW8iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6IlhhbWJiaDNVOHZHRVFOMy0wcVFGREEiLCJuYW1lIjoiSm9obiBQYXVsIEtwYW50ZXkiLCJnaXZlbl9uYW1lIjoiSm9obiBQYXVsIiwiZmFtaWx5X25hbWUiOiJLcGFudGV5IiwibG9jYWxlIjoiZW4iLCJpYXQiOjE2ODE5ODk3MTcsImV4cCI6MTY4MTk5MzMxN30.lKjhekMFkD14ES3af5HPDnave2elfdfUoqMn7gaV1R9Z1FUvmlHW46hpyinfKmWfGzc3E-5hZZSuhugUc-TGJa8eBpS796HHSAhdivgQIB5nU_zfmhcXPfoP2GYzdxhfohfe-5gOnwiwP6W4AlrNLr4mqGLA3HqruRtlm5dT6UzwnlcZzotU04rUYGngFWkCtBdvPhS3xjeXBGABIHUDG6ydovYcfkQ1dzW1YLq5EnD0Y2VkRVpPgJByB1uWE8n3DLRu3AGwE_a9IlPVxacYxuZLP3bcz1On1WTC3m4WrqsA8n_LWloq9cJEBzfiF94uWr2iw72Fb_H3q9bp4TcBJw";
        // Dead token or incorrect token
        unauthorisedToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjFhYWU4ZDdjOTIwNThiNWVlYTQ1Njg5NWJmODkwODQ1NzFlMzA2ZjMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI3NzI2NTQzNDAxODgtOWNvbzkwMm9kOG1pMDdhZmE3Mmo0amtwZjliMTlmbTguYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI3NzI2NTQzNDAxODgtOWNvbzkwMm9kOG1pMDdhZmE3Mmo0amtwZjliMTlmbTguYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTAyMjQ4NzEzMDQxNTAxMDA0NzIiLCJoZCI6InR1cm50YWJsLmlvIiwiZW1haWwiOiJqb2huLmtwYW50ZXlAdHVybnRhYmwuaW8iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6InhBanZVSS1wblY2dnNLOHM5eFhYU1EiLCJuYW1lIjoiSm9obiBQYXVsIEtwYW50ZXkiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUdObXl4WWtQUUV2SEFsMncxTlRXUEt6ZXliNVQ2WHBlZVV3NTM4blg0dnY9czk2LWMiLCJnaXZlbl9uYW1lIjoiSm9obiBQYXVsIiwiZmFtaWx5X25hbWUiOiJLcGFudGV5IiwibG9jYWxlIjoiZW4iLCJpYXQiOjE2ODA1Mzg0MjksImV4cCI6MTY4MDU0MjAyOX0.XLY2lsIBwjSs6v4nbe5T5CmuBbTpguRb3Hwn1Wm-izYjdROK_a0Ff7EqV4Ztwhg_-BNziF0lIZLbvyeCgOfxT10_OwzDCjrr3PB_1gWqqfCvFMwdvz-OgVuBhg7qyCv5GAJfJCuqu0MCmmWiai_EI68fBG8LTiZf_upVaZAvSJrjNpGQTvGzXK7VpKL4DpTJwVCSQ4aiU94wIDzrPeej0JsOQT0WdtAOpzBz9xY9cwD7QAPCHbcsrIEkdyqKdybRgoXDRnJnJnKjtkC3o9mpFy6cPJeT4n5Mr-LCHSHoRzPezaFOWMsKeHV_ac9w1IVbvGELK1PFGR1oQ9ZHCMsdcQ";

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testLoginUserWithInvalidTokenReturns401ExceptionAndInvalidTokenErrorMessage() throws Exception {
        MvcResult errorResult = mockMvc.perform(get("/api/v1/user/login")
                                        .header("Authorization", "Bearer " + unauthorisedToken))
                                        .andExpect(status().isUnauthorized())
                                        .andReturn();

        String content = (String) errorResult.getResponse().getHeaderValue("WWW-Authenticate");
        assert content != null;
        assertEquals("invalid_token", content.substring(14,27));

    }

    @Test
    void testLoginUserWithCorrectTokenReturnsExpectedResponse() throws Exception{
        MvcResult result = mockMvc.perform(get("/api/v1/user/login").header("Authorization", "Bearer " + correctToken))
                                    .andExpect(status().isOk())
                                    .andReturn();
        String content = result.getResponse().getContentAsString();
        TTResponse myResponse = TTResponse.builder().statusCode(200).statusText("Authorised").message("Secured endpoint").build();
        String expectedResponse = new ObjectMapper().writeValueAsString(myResponse);
        assertEquals(expectedResponse, content); }
}