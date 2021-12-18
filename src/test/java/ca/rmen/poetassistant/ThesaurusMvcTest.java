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

import static ca.rmen.poetassistant.api.ThesaurusController.QUERY_PARAM_WORD;
import static ca.rmen.poetassistant.api.ThesaurusController.SERVICE;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class ThesaurusMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testThesaurusOk() throws Exception {
        mockMvc.perform(get(getRequestUrl("intuition")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("hunch")));
    }

    @Test
    void testThesaurusNotFound() throws Exception {
        mockMvc.perform(get(getRequestUrl("blahblahblah")))
                .andExpect(status().isNotFound());
    }

    @Test
    void testThesaurusEmptyParameter() throws Exception {
        mockMvc.perform(get(getRequestUrl("")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testThesaurusMissingParameter() throws Exception {
        mockMvc.perform(get(SERVICE))
                .andExpect(status().isBadRequest());
    }

    private String getRequestUrl(String word) {
        return String.format("%s?%s=%s", SERVICE, QUERY_PARAM_WORD, word);
    }
}
