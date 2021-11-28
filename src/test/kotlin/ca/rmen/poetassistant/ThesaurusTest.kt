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

import ca.rmen.poetassistant.restservice.common.model.PartOfSpeech
import ca.rmen.poetassistant.restservice.thesaurus.ThesaurusController
import ca.rmen.poetassistant.restservice.thesaurus.model.ThesaurusEntryModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ThesaurusTest {

    @Autowired
    private lateinit var controller: ThesaurusController

    @Test
    fun testThesaurusEntriesOnlySynonyms() {
        val actualThesaurusEntries = controller.getThesaurusEntries("nightmare")
        val expectedThesaurusEntries = listOf(
            ThesaurusEntryModel(
                partOfSpeech = PartOfSpeech.NOUN,
                synonyms = listOf("incubus", "situation"),
                antonyms = emptyList()
            ),
            ThesaurusEntryModel(
                partOfSpeech = PartOfSpeech.NOUN,
                synonyms = listOf("dream", "dreaming"),
                antonyms = emptyList()
            )
        )
        assertEquals(expectedThesaurusEntries, actualThesaurusEntries)
    }

    @Test
    fun testThesaurusEntriesMixedSynonymsAndAntonyms() {
        val actualThesaurusEntries = controller.getThesaurusEntries("unstructured")
        val expectedThesaurusEntries = listOf(
            ThesaurusEntryModel(
                partOfSpeech = PartOfSpeech.ADJECTIVE,
                synonyms = listOf("ambiguous", "unorganised", "unorganized", "unregulated"),
                antonyms = listOf("structured")
            ),
            ThesaurusEntryModel(
                partOfSpeech = PartOfSpeech.ADJECTIVE,
                synonyms = listOf("amorphous", "inorganic"),
                antonyms = emptyList()
            )
        )
        assertEquals(expectedThesaurusEntries, actualThesaurusEntries)
    }
}
