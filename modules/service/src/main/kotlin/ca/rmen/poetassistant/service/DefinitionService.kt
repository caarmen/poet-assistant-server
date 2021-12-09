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

package ca.rmen.poetassistant.service

import ca.rmen.poetassistant.repository.definitions.DefinitionRepository
import ca.rmen.poetassistant.service.model.DefinitionServiceModel
import ca.rmen.poetassistant.service.model.PartOfSpeechService
import org.springframework.stereotype.Service

@Service
class DefinitionService(private val repository: DefinitionRepository) {

    fun findDefinitions(word: String): List<DefinitionServiceModel> =
        repository.findAllByWord(word)
            .map {
                DefinitionServiceModel(
                    partOfSpeech = it.partOfSpeech.toPartOfSpeech,
                    definition = it.definition
                )
            }

    private val String.toPartOfSpeech
        get() = when (this) {
            "a" -> PartOfSpeechService.ADJECTIVE
            "n" -> PartOfSpeechService.NOUN
            "r" -> PartOfSpeechService.ADVERB
            "v" -> PartOfSpeechService.VERB
            else -> PartOfSpeechService.UNKNOWN
        }
}