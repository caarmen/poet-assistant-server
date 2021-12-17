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

package ca.rmen.poetassistant.service;

import ca.rmen.poetassistant.repository.definitions.DefinitionEntity;
import ca.rmen.poetassistant.repository.definitions.DefinitionRepository;
import ca.rmen.poetassistant.service.model.DefinitionServiceModel;
import ca.rmen.poetassistant.service.model.PartOfSpeechService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefinitionService {

    private final DefinitionRepository repository;

    public DefinitionService(DefinitionRepository repository) {
        this.repository = repository;
    }

    public List<DefinitionServiceModel> findDefinitions(String word) {
        return repository.findAllByWord(word)
                .stream()
                .map((DefinitionEntity it) -> new DefinitionServiceModel(
                        toPartOfSpeech(it.partOfSpeech),
                        it.definition
                ))
                .toList();
    }

    private PartOfSpeechService toPartOfSpeech(String partOfSpeechString) {
        return switch (partOfSpeechString) {
            case "a" -> PartOfSpeechService.ADJECTIVE;
            case "n" -> PartOfSpeechService.NOUN;
            case "r" -> PartOfSpeechService.ADVERB;
            case "v" -> PartOfSpeechService.VERB;
            default -> PartOfSpeechService.UNKNOWN;
        };
    }
}