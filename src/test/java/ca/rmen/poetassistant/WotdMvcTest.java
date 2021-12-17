/*
 * Copyright (c) 2021 - present Carmen Alvarez
 *
 * This file is part of Poet Assistant.
 *
 * Poet Assistant is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Poet Assistant is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Poet Assistant.  If not, see <http://www.gnu.org/licenses/>.
 */

package ca.rmen.poetassistant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.stream.Collectors;

import static ca.rmen.poetassistant.api.WotdController.QUERY_PARAM_BEFORE;
import static ca.rmen.poetassistant.api.WotdController.QUERY_PARAM_SIZE;
import static ca.rmen.poetassistant.api.WotdController.SERVICE;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class WotdMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testWotdTodayOk() throws Exception {
        mockMvc.perform(get(SERVICE))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("word")));
    }

    /**
     * For a query requesting 3 days ending 2021-11-22:
     * Here's a snippet of a few days of words:
     * 2021-11-23: cytokinesis (shouldn't be included)
     * 2021-11-22: airfield
     * 2021-11-21: teem
     * 2021-11-20: traitorous
     * 2021-11-19: unplug (shouldn't be included)
     */
    @Test
    void testWotdSpecificDateListOk() throws Exception {
        mockMvc.perform(get(getRequestUrl(Map.of(QUERY_PARAM_BEFORE, "2021-11-22", QUERY_PARAM_SIZE, "3"))))
                .andExpect(status().isOk())
                .andExpect(content().string(not(containsString("cytokinesis"))))
                .andExpect(content().string(containsString("airfield")))
                .andExpect(content().string(containsString("traitorous")))
                .andExpect(content().string(not(containsString("unplug"))));
    }

    @Test
    void testWotdSpecificDateOneItemOk() throws Exception {
        mockMvc.perform(get(getRequestUrl(Map.of(QUERY_PARAM_BEFORE, "2021-11-21"))))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("teem")));
    }

    @Test
    void testWordTooBigPage() throws Exception {
        mockMvc.perform(get(getRequestUrl(Map.of(QUERY_PARAM_SIZE, "500"))))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testWordZeroSizePage() throws Exception {
        mockMvc.perform(get(getRequestUrl(Map.of(QUERY_PARAM_SIZE, "0"))))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testWordNegativeSizePage() throws Exception {
        mockMvc.perform(get(getRequestUrl(Map.of(QUERY_PARAM_SIZE, "-5"))))
                .andExpect(status().isBadRequest());
    }

    private String getRequestUrl(Map<String, String> parameters) {
        return String.format("%s?%s",
                SERVICE,
                parameters
                        .entrySet()
                        .stream()
                        .map((it) -> String.format("%s=%s", it.getKey(), it.getValue()))
                        .collect(Collectors.joining("&")));
    }

}
