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

package ca.rmen.poetassistant.restservice.definitions

import ca.rmen.poetassistant.restservice.InputValidator
import ca.rmen.poetassistant.restservice.definitions.jpa.DefinitionRepository
import ca.rmen.poetassistant.restservice.definitions.model.DefinitionModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DefinitionController {
    @Autowired
    private lateinit var repository: DefinitionRepository

    @GetMapping("/definition")
    fun definition(@RequestParam("word") word: String): ResponseEntity<List<DefinitionModel>> {
        InputValidator.validateNotBlank("word", word)
        return repository.findAllByWord(word.lowercase())
            .map {
                DefinitionModel(
                    word = it.word,
                    partOfSpeech = it.partOfSpeech,
                    definition = it.definition
                )
            }.takeIf { it.isNotEmpty() }
            ?.let { ResponseEntity.ok(it) } ?: ResponseEntity.status(HttpStatus.NOT_FOUND)
            .build()
    }
}
