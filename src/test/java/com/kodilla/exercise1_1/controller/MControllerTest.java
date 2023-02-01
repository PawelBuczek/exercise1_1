package com.kodilla.exercise1_1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(includeFilters = @ComponentScan.Filter(classes = EnableWebSecurity.class))
class MControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    public void withAnonymousM1_Unauthorized() throws Exception {
        mockMvc.perform(get("/m1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    public void withAnonymousM2_Unauthorized() throws Exception {
        mockMvc.perform(get("/m2"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    public void withAnonymousM3_Unauthorized() throws Exception {
        mockMvc.perform(get("/m3"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails("R1")
    public void withR1M1_OK() throws Exception {
        mockMvc.perform(get("/m1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("R2")
    public void withR2M1_OK() throws Exception {
        mockMvc.perform(get("/m1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("R3")
    public void withR3M1_OK() throws Exception {
        mockMvc.perform(get("/m1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("R1")
    public void withR1M2_Forbidden() throws Exception {
        mockMvc.perform(get("/m2"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("R2")
    public void withR2M2_Ok() throws Exception {
        mockMvc.perform(get("/m2"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("R3")
    public void withR3M2_Ok() throws Exception {
        mockMvc.perform(get("/m2"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("R1")
    public void withR1M3_Forbidden() throws Exception {
        mockMvc.perform(get("/m3"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("R2")
    public void withR2M3_Forbidden() throws Exception {
        mockMvc.perform(get("/m3"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("R3")
    public void withR3M3_Ok() throws Exception {
        mockMvc.perform(get("/m3"))
                .andExpect(status().isOk());
    }
}