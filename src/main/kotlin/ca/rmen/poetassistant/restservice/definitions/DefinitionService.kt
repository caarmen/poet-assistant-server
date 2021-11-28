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

import ca.rmen.poetassistant.restservice.common.model.PartOfSpeech
import ca.rmen.poetassistant.restservice.definitions.jpa.DefinitionRepository
import ca.rmen.poetassistant.restservice.definitions.model.DefinitionModel
import org.springframework.stereotype.Service

@Service
class DefinitionService(private val repository: DefinitionRepository) {

    fun findDefinitions(word: String): List<DefinitionModel> =
        repository.findAllByWord(word)
            .map {
                DefinitionModel(
                    partOfSpeech = it.partOfSpeech.toPartOfSpeech,
                    definition = it.definition
                )
            }

    private val String.toPartOfSpeech
        get() = when (this) {
            "a" -> PartOfSpeech.ADJECTIVE
            "n" -> PartOfSpeech.NOUN
            "r" -> PartOfSpeech.ADVERB
            "v" -> PartOfSpeech.VERB
            else -> PartOfSpeech.UNKNOWN
        }
}