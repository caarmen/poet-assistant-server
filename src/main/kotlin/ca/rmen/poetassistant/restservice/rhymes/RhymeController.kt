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

package ca.rmen.poetassistant.restservice.rhymes

import ca.rmen.poetassistant.restservice.common.ResponseValidator.validateResultNotEmpty
import ca.rmen.poetassistant.restservice.rhymes.model.WordRhymesModel
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.NotBlank

@RestController
@Validated
class RhymeController(private val service: RhymeService) {
    companion object {
        const val SERVICE = "/rhymes"
        const val QUERY_PARAM_WORD = "word"
    }

    @GetMapping(SERVICE)
    fun getRhymes(@RequestParam(QUERY_PARAM_WORD) @NotBlank word: String): List<WordRhymesModel> =
        service.findRhymes(word.lowercase()).validateResultNotEmpty(word)
    // TODO for now we only return words which match stress syllables
    // We should also return words which match the last one, two, or three syllables
}
