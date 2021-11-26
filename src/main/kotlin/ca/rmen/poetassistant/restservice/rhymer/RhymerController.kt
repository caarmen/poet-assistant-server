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

package ca.rmen.poetassistant.restservice.rhymer

import ca.rmen.poetassistant.restservice.RequestValidator.validateInputNotBlank
import ca.rmen.poetassistant.restservice.RequestValidator.validateResultNotEmpty
import ca.rmen.poetassistant.restservice.rhymer.jpa.RhymerRepository
import ca.rmen.poetassistant.restservice.rhymer.model.SyllableRhymesModel
import ca.rmen.poetassistant.restservice.rhymer.model.WordRhymesModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RhymerController {
    companion object {
        private const val QUERY_PARAM_WORD = "word"
    }

    @Autowired
    private lateinit var repository: RhymerRepository

    @GetMapping("/rhymes")
    fun rhymes(@RequestParam(QUERY_PARAM_WORD) word: String): List<WordRhymesModel> =
        repository.findAllByWord(
            word.lowercase().validateInputNotBlank(QUERY_PARAM_WORD)
        ).map { wordVariant ->
            WordRhymesModel(
                variantNumber = wordVariant.variantNumber,
                stressRhymes = SyllableRhymesModel(
                    syllables = wordVariant.stressSyllables,
                    rhymes = repository.findByStressSyllablesAndWordNotOrderByWord(
                        wordVariant.stressSyllables,
                        word
                    ).map { it.word }
                        .distinct()
                )
            )
        }.validateResultNotEmpty(word)
    // TODO for now we only return words which match stress syllables
    // We should also return words which match the last one, two, or three syllables
}
