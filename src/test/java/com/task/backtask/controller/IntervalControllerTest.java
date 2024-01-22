package com.task.backtask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.backtask.entity.DigitInterval;
import com.task.backtask.entity.LetterInterval;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class IntervalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void mergeDigitsAndFindMinTest() throws Exception {
        var request = List.of(
                Arrays.asList(1, 4),
                Arrays.asList(3, 6),
                Arrays.asList(8, 10)
        );

        var expectedMerge = List.of(
                List.of(1, 6),
                List.of(8, 10)
        );

        var mergeResult = mockMvc.perform(post("/api/v1/intervals/merge")
                        .contentType("application/json")
                        .param("kind", "digits")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mergeResult.getResponse().getContentAsString()).isEqualTo(expectedMerge.toString());

        var expectedMin = new DigitInterval(1, 6);

        var minResult = mockMvc.perform(get("/api/v1/intervals/min")
                        .contentType("application/json")
                        .param("kind", "digits"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(minResult.getResponse().getContentAsString()).isEqualTo(expectedMin.toString());
    }

    @Test
    void mergeLettersAndFindMinTest() throws Exception {
        var request = List.of(
                Arrays.asList("a", "f"),
                Arrays.asList("d", "j"),
                Arrays.asList("r", "z")
        );

        var expectedMerge = List.of(
                List.of("a", "j"),
                List.of("r", "z")
        );

        var mergeResult = mockMvc.perform(post("/api/v1/intervals/merge")
                        .contentType("application/json")
                        .param("kind", "letters")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mergeResult.getResponse().getContentAsString()).isEqualTo(expectedMerge.toString());

        var expectedMin = new LetterInterval("a", "j");

        var minResult = mockMvc.perform(get("/api/v1/intervals/min")
                        .contentType("application/json")
                        .param("kind", "letters"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(minResult.getResponse().getContentAsString()).isEqualTo(expectedMin.toString());
    }

    @Test
    void mergeIllegalKindTest() throws Exception {
        var request = List.of(Arrays.asList("a", "f"));

        mockMvc.perform(post("/api/v1/intervals/merge")
                        .contentType("application/json")
                        .param("kind", "abc")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}