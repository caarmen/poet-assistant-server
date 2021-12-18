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

import ca.rmen.poetassistant.api.DefinitionController;
import ca.rmen.poetassistant.api.RhymeController;
import ca.rmen.poetassistant.api.ThesaurusController;
import ca.rmen.poetassistant.api.WotdController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;

import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "build/snippets")
class GenerateRestDocSnippetsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGenerateRhymesSnippet() throws Exception {
        mockMvc.perform(get(getRequestUrl(RhymeController.SERVICE, Map.of(RhymeController.QUERY_PARAM_WORD, "dove"))))
                .andDo(documentPretty(RhymeController.SERVICE));
    }

    @Test
    void testGenerateThesaurusSnippet() throws Exception {
        mockMvc.perform(get(getRequestUrl(ThesaurusController.SERVICE, Map.of(ThesaurusController.QUERY_PARAM_WORD, "happy"))))
                .andDo(documentPretty(ThesaurusController.SERVICE));
    }

    @Test
    void testGenerateDefinitionsSnippet() throws Exception {
        mockMvc.perform(get(getRequestUrl(DefinitionController.SERVICE, Map.of(DefinitionController.QUERY_PARAM_WORD, "baffle"))))
                .andDo(documentPretty(DefinitionController.SERVICE));
    }

    @Test
    void testGenerateWotdTodaySnippet() throws Exception {
        mockMvc.perform(get(WotdController.SERVICE))
                .andDo(documentPretty(String.format("%s/%s", WotdController.SERVICE, "today")));
    }

    @Test
    void testGenerateWotdListSnippet() throws Exception {
        mockMvc.perform(get(getRequestUrl(WotdController.SERVICE, Map.of(WotdController.QUERY_PARAM_BEFORE, "2021-12-31", WotdController.QUERY_PARAM_SIZE, "7"))))
                .andDo(documentPretty(String.format("%s/%s", WotdController.SERVICE, "list")));
    }

    private String getRequestUrl(String service, Map<String, String> params) {
        return String.format("%s?%s",
                service,
                params.entrySet()
                        .stream()
                        .map((entry) -> String.format("%s=%s", entry.getKey(), entry.getValue()))
                        .collect(Collectors.joining("&"))
        );
    }

    private ResultHandler documentPretty(String path) {
        return document(
                path.replaceFirst("^/", ""),
                preprocessResponse(prettyPrint())
        );
    }
}
