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

package ca.rmen.poetassistant.restservice.thesaurus

import ca.rmen.poetassistant.restservice.common.model.PartOfSpeech
import ca.rmen.poetassistant.restservice.thesaurus.jpa.ThesaurusRepository
import ca.rmen.poetassistant.restservice.thesaurus.model.ThesaurusEntryModel
import org.springframework.stereotype.Service

@Service
class ThesaurusService(private val repository: ThesaurusRepository) {
    fun findThesaurusEntries(word: String): List<ThesaurusEntryModel> =
        repository.findAllByWord(word)
            .map {
                ThesaurusEntryModel(
                    partOfSpeech = it.wordType.toPartOfSpeech,
                    synonyms = it.synonyms.toWordList(),
                    antonyms = it.antonyms.toWordList()
                )
            }

    /**
     * In our database, the synonyms of a word are stored in a single
     * column, as a comma-separated list of words. We need to convert
     * this into a sorted list of words
     */
    private fun String.toWordList() =
        split(",")
            .filter { it.isNotEmpty() }
            .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it })

    private val String.toPartOfSpeech
        get() = when (this) {
            "ADJ" -> PartOfSpeech.ADJECTIVE
            "NOUN" -> PartOfSpeech.NOUN
            "ADV" -> PartOfSpeech.ADVERB
            "VERB" -> PartOfSpeech.VERB
            else -> PartOfSpeech.UNKNOWN
        }
}