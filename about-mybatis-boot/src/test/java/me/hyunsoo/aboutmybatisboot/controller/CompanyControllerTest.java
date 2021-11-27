package me.hyunsoo.aboutmybatisboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.hyunsoo.aboutmybatisboot.beans.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class CompanyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void insertCompany() throws Exception {
        Company company = new Company();
        company.setName("롯데마트");
        company.setAddress("서울특별시 송파구 잠실동");

        String json = objectMapper.writeValueAsString(company);

        System.out.println(json);

        mockMvc.perform(MockMvcRequestBuilders.post("/company")
        .content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void selectAllCompany() throws Exception{

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/company"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        String json = result.getResponse().getContentAsString(Charset.forName("UTF-8"));

        TypeReference<List<Company>> typeReference = new TypeReference<List<Company>>(){};

        objectMapper.readValue(json, typeReference).stream().forEach(System.out::println);

    }



}