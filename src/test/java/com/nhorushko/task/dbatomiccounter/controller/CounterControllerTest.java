package com.nhorushko.task.dbatomiccounter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhorushko.task.dbatomiccounter.config.PostgresInMemoryConfig;
import com.nhorushko.task.dbatomiccounter.model.CounterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = PostgresInMemoryConfig.class)
class CounterControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    private static final String URL_COUNTER_MODIFY = "/counter/modify";

    @Test
    @Transactional
    @Sql("classpath:scripts/add-counter-1-with-value-10.sql")
    void modify_shouldReturn200WithIncrementedResult() throws Exception {
        var given = new CounterRequest(1, 15);
        postResponse(URL_COUNTER_MODIFY, given, 200, "{\"current\":25}");
    }

    @Test
    @Transactional
    @Sql("classpath:scripts/add-counter-1-with-value-10.sql")
    void modify_counterNotFoundShouldReturn418AndErrorMessage() throws Exception {
        var given = new CounterRequest(9999, 15);
        postResponse(URL_COUNTER_MODIFY, given, 418, "{\"message\":\"Incorrect result size: expected 1, actual 0\"}");
    }

    protected void postResponse(String url, CounterRequest request, int expectedCode, String expectedBody) throws Exception {
        var req = post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));
        String actualBody = this.mockMvc
                .perform(req)
                .andDo(print())
                .andExpect(status().is(expectedCode))
                .andReturn().getResponse().getContentAsString();
        assertEquals(expectedBody, actualBody, true);
    }
}