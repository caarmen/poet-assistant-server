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

import ca.rmen.poetassistant.restservice.definitions.DefinitionController
import ca.rmen.poetassistant.restservice.rhymes.RhymeController
import ca.rmen.poetassistant.restservice.thesaurus.ThesaurusController
import ca.rmen.poetassistant.restservice.wotd.WotdController
import ca.rmen.poetassistant.GenerateRestDocSnippetsTest.Companion.REST_DOCS_OUTPUT_DIR
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = REST_DOCS_OUTPUT_DIR)
class GenerateRestDocSnippetsTest {
    companion object {
        const val REST_DOCS_OUTPUT_DIR = "build/snippets"
        val String.serviceDocDir get() = removePrefix("/")
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun testGenerateRhymesSnippet() {
        mockMvc.perform(MockMvcRequestBuilders.get("${RhymeController.SERVICE}?${RhymeController.QUERY_PARAM_WORD}=dove"))
            .andDo(document(RhymeController.SERVICE.serviceDocDir))
    }

    @Test
    fun testGenerateThesaurusSnippet() {
        mockMvc.perform(MockMvcRequestBuilders.get("${ThesaurusController.SERVICE}?${ThesaurusController.QUERY_PARAM_WORD}=happy"))
            .andDo(document(ThesaurusController.SERVICE.serviceDocDir))
    }

    @Test
    fun testGenerateDefinitionsSnippet() {
        mockMvc.perform(MockMvcRequestBuilders.get("${DefinitionController.SERVICE}?${DefinitionController.QUERY_PARAM_WORD}=happy"))
            .andDo(document(DefinitionController.SERVICE.serviceDocDir))
    }

    @Test
    fun testGenerateWotdTodaySnippet() {
        mockMvc.perform(MockMvcRequestBuilders.get(WotdController.SERVICE))
            .andDo(document("${WotdController.SERVICE.serviceDocDir}/today"))
    }

    @Test
    fun testGenerateWotdListSnippet() {
        mockMvc.perform(MockMvcRequestBuilders.get("${WotdController.SERVICE}?${WotdController.QUERY_PARAM_BEFORE}=2021-12-31&${WotdController.QUERY_PARAM_SIZE}=7"))
            .andDo(document("${WotdController.SERVICE.serviceDocDir}/list"))
    }
}
