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

import ca.rmen.poetassistant.api.ThesaurusController;
import ca.rmen.poetassistant.api.model.PartOfSpeechApi;
import ca.rmen.poetassistant.api.model.ThesaurusEntryApiModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ThesaurusServiceTest {

    @Autowired
    private ThesaurusController controller;

    @Test
    void testThesaurusEntriesOnlySynonyms() {
        var actualThesaurusEntries = controller.getThesaurusEntries("nightmare");
        var expectedThesaurusEntries = List.of(
                new ThesaurusEntryApiModel(
                        PartOfSpeechApi.NOUN,
                        List.of("incubus", "situation"),
                        Collections.emptyList()
                ),
                new ThesaurusEntryApiModel(
                        PartOfSpeechApi.NOUN,
                        List.of("dream", "dreaming"),
                        Collections.emptyList()
                )
        );
        assertEquals(expectedThesaurusEntries, actualThesaurusEntries);
    }

    @Test
    void testThesaurusEntriesMixedSynonymsAndAntonyms() {
        var actualThesaurusEntries = controller.getThesaurusEntries("unstructured");
        var expectedThesaurusEntries = List.of(
                new ThesaurusEntryApiModel(
                        PartOfSpeechApi.ADJECTIVE,
                        List.of("ambiguous", "unorganised", "unorganized", "unregulated"),
                        List.of("structured")
                ),
                new ThesaurusEntryApiModel(
                        PartOfSpeechApi.ADJECTIVE,
                        List.of("amorphous", "inorganic"),
                        Collections.emptyList()
                )
        );
        assertEquals(expectedThesaurusEntries, actualThesaurusEntries);
    }
}
