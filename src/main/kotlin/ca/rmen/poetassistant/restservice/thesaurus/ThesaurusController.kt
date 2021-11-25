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

import ca.rmen.poetassistant.restservice.InputValidator
import ca.rmen.poetassistant.restservice.thesaurus.jpa.ThesaurusRepository
import ca.rmen.poetassistant.restservice.thesaurus.model.ThesaurusEntryModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ThesaurusController {
    @Autowired
    private lateinit var repository: ThesaurusRepository

    @GetMapping("/thesaurus")
    fun thesaurus(@RequestParam("word") word: String): ResponseEntity<List<ThesaurusEntryModel>> {
        InputValidator.validateNotBlank("word", word)
        return repository.findAllByWord(word)
            .map {
                ThesaurusEntryModel(
                    partOfSpeech = it.wordType,
                    synonyms = it.synonyms.split(",")
                        .filter { synonym -> synonym.isNotEmpty() }
                        .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it }),
                    antonyms = it.antonyms.split(",")
                        .filter { antonym -> antonym.isNotEmpty() }
                        .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it }),
                )
            }.takeIf { it.isNotEmpty() }
            ?.let { ResponseEntity.ok(it) } ?: ResponseEntity.status(HttpStatus.NOT_FOUND)
            .build()
    }
}