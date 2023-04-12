package com.ttrides.turntablRides.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttrides.turntablRides.model.response.TTResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        correctToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImFjZGEzNjBmYjM2Y2QxNWZmODNhZjgzZTE3M2Y0N2ZmYzM2ZDExMWMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI0NTk2MTEyODcxMDYtdmEyZGszaWcycDJ2YW03dGZydXY4OHVmdDRvdWVibzkuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI0NTk2MTEyODcxMDYtdmEyZGszaWcycDJ2YW03dGZydXY4OHVmdDRvdWVibzkuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTAyMjQ4NzEzMDQxNTAxMDA0NzIiLCJoZCI6InR1cm50YWJsLmlvIiwiZW1haWwiOiJqb2huLmtwYW50ZXlAdHVybnRhYmwuaW8iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6IjBEaFNrcXFkMC1zRWpkY3pGNUlHblEiLCJuYW1lIjoiSm9obiBQYXVsIEtwYW50ZXkiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUdObXl4WWtQUUV2SEFsMncxTlRXUEt6ZXliNVQ2WHBlZVV3NTM4blg0dnY9czk2LWMiLCJnaXZlbl9uYW1lIjoiSm9obiBQYXVsIiwiZmFtaWx5X25hbWUiOiJLcGFudGV5IiwibG9jYWxlIjoiZW4iLCJpYXQiOjE2ODEzMjM0MzgsImV4cCI6MTY4MTMyNzAzOH0.SLRJ91-jnxsRg9PBbq1O40ZZ9DqqSbuhnRqM0oyxBG4-WYLLs_tVm7duNctaIoESKi2sXe7Pcv_OXCBuxUVhlHnWSTndTIoySYBC4OZ_EmoFk-XkOfHbHuh6YYIgAgrb3Fko_Mvnf4dCB5ahIuwh0o8WEqZNZSLpY5oHJ-m4E7Sgl_8E_lmvTMz3sHi1Ll8_NRcmaeZ_dS_LWlPDO0LD2nFvJiuzSw0w_803Fh1SI4mwwZzMFUwpp6VJpSfaLX81E4GjbhZ4QT3wHAFd2DH5VS2Lw6u6rjxpXQAA-cQIJPnoedJeN-jdXO2Ye72r0kJ2NTSwVCEhUWh5umzAAF_F-A";
        // Dead token or incorrect token
        unauthorisedToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjFhYWU4ZDdjOTIwNThiNWVlYTQ1Njg5NWJmODkwODQ1NzFlMzA2ZjMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI3NzI2NTQzNDAxODgtOWNvbzkwMm9kOG1pMDdhZmE3Mmo0amtwZjliMTlmbTguYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI3NzI2NTQzNDAxODgtOWNvbzkwMm9kOG1pMDdhZmE3Mmo0amtwZjliMTlmbTguYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTAyMjQ4NzEzMDQxNTAxMDA0NzIiLCJoZCI6InR1cm50YWJsLmlvIiwiZW1haWwiOiJqb2huLmtwYW50ZXlAdHVybnRhYmwuaW8iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6InhBanZVSS1wblY2dnNLOHM5eFhYU1EiLCJuYW1lIjoiSm9obiBQYXVsIEtwYW50ZXkiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUdObXl4WWtQUUV2SEFsMncxTlRXUEt6ZXliNVQ2WHBlZVV3NTM4blg0dnY9czk2LWMiLCJnaXZlbl9uYW1lIjoiSm9obiBQYXVsIiwiZmFtaWx5X25hbWUiOiJLcGFudGV5IiwibG9jYWxlIjoiZW4iLCJpYXQiOjE2ODA1Mzg0MjksImV4cCI6MTY4MDU0MjAyOX0.XLY2lsIBwjSs6v4nbe5T5CmuBbTpguRb3Hwn1Wm-izYjdROK_a0Ff7EqV4Ztwhg_-BNziF0lIZLbvyeCgOfxT10_OwzDCjrr3PB_1gWqqfCvFMwdvz-OgVuBhg7qyCv5GAJfJCuqu0MCmmWiai_EI68fBG8LTiZf_upVaZAvSJrjNpGQTvGzXK7VpKL4DpTJwVCSQ4aiU94wIDzrPeej0JsOQT0WdtAOpzBz9xY9cwD7QAPCHbcsrIEkdyqKdybRgoXDRnJnJnKjtkC3o9mpFy6cPJeT4n5Mr-LCHSHoRzPezaFOWMsKeHV_ac9w1IVbvGELK1PFGR1oQ9ZHCMsdcQ";

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void loginUser_whenProvidedUnauthorisedOrExpiredOrInvalidToken_ShouldReturnInvalid_tokenWith401Exception() throws Exception {
        MvcResult errorResult = mockMvc.perform(get("/api/v1/login")
                                        .header("Authorization", "Bearer " + unauthorisedToken))
                                        .andExpect(status().isUnauthorized())
                                        .andReturn();

        String content = (String) errorResult.getResponse().getHeaderValue("WWW-Authenticate");
        assert content != null;
        assertTrue(content.contains("invalid_token"));
    }

    @Test
    void loginUser_WhenProvidedCorrectToken_ShouldBeOkWith200ResponseAndExactResponseObject() throws Exception{
        MvcResult result = mockMvc.perform(get("/api/v1/login").header("Authorization", "Bearer " + correctToken))
                                    .andExpect(status().isOk())
                                    .andReturn();
        String content = result.getResponse().getContentAsString();
        TTResponse myResponse = TTResponse.builder().statusCode(200).statusText("Authorised").message("Secured endpoint").build();
        String expectedResponse = new ObjectMapper().writeValueAsString(myResponse);
        assertEquals(expectedResponse, content); }
}