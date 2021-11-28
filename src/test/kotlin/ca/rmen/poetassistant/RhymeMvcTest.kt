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

package ca.rmen.poetassistant

import ca.rmen.poetassistant.restservice.rhymes.RhymeController.Companion.QUERY_PARAM_WORD
import ca.rmen.poetassistant.restservice.rhymes.RhymeController.Companion.SERVICE
import org.hamcrest.core.StringContains.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@AutoConfigureMockMvc
@SpringBootTest
class RhymeMvcTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun testRhymesOk() {
        mockMvc.perform(get("${SERVICE}?${QUERY_PARAM_WORD}=heart"))
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("cart")))
    }

    @Test
    fun testRhymesNotFound() {
        mockMvc.perform(get("${SERVICE}?${QUERY_PARAM_WORD}=nothinghere"))
            .andExpect(status().isNotFound)
    }

    @Test
    fun testRhymesEmptyParameter() {
        mockMvc.perform(get("${SERVICE}?${QUERY_PARAM_WORD}="))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun testRhymesMissingParameter() {
        mockMvc.perform(get(SERVICE))
            .andExpect(status().isBadRequest)
    }
}
