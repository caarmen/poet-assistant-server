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

import ca.rmen.poetassistant.api.WotdController.Companion.QUERY_PARAM_BEFORE
import ca.rmen.poetassistant.api.WotdController.Companion.QUERY_PARAM_SIZE
import ca.rmen.poetassistant.api.WotdController.Companion.SERVICE
import org.hamcrest.Matchers.not
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
class WotdMvcTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun testWotdTodayOk() {
        mockMvc.perform(get(SERVICE))
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("word")))
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
    fun testWotdSpecificDateListOk() {
        mockMvc.perform(get("$SERVICE?$QUERY_PARAM_BEFORE=2021-11-22&$QUERY_PARAM_SIZE=3"))
            .andExpect(status().isOk)
            .andExpect(content().string(not(containsString("cytokinesis"))))
            .andExpect(content().string(containsString("airfield")))
            .andExpect(content().string(containsString("traitorous")))
            .andExpect(content().string(not(containsString("unplug"))))
    }

    @Test
    fun testWotdSpecificDateOneItemOk() {
        mockMvc.perform(get("$SERVICE?$QUERY_PARAM_BEFORE=2021-11-21"))
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("teem")))
    }

    @Test
    fun testWordTooBigPage() {
        mockMvc.perform(get("${SERVICE}?${QUERY_PARAM_SIZE}=500"))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun testWordZeroSizePage() {
        mockMvc.perform(get("${SERVICE}?${QUERY_PARAM_SIZE}=0"))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun testWordNegativeSizePage() {
        mockMvc.perform(get("${SERVICE}?${QUERY_PARAM_SIZE}=-5"))
            .andExpect(status().isBadRequest)
    }
}
