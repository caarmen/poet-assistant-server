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

import ca.rmen.poetassistant.repository.rhymes.RhymerRepository;
import ca.rmen.poetassistant.service.model.SyllableRhymesServiceModel;
import ca.rmen.poetassistant.service.model.WordRhymesServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RhymeService {
    private final RhymerRepository repository;

    public RhymeService(RhymerRepository repository) {
        this.repository = repository;
    }

    public List<WordRhymesServiceModel> findRhymes(String word) {
        return repository.findAllByWord(word)
                .stream()
                .map((wordVariant) -> new WordRhymesServiceModel(
                        wordVariant.variantNumber,
                        Optional.of(new SyllableRhymesServiceModel(
                                wordVariant.stressSyllables,
                                repository.findByStressSyllablesAndWordNotOrderByWord(
                                                wordVariant.stressSyllables,
                                                word
                                        )
                                        .stream()
                                        .map((it) -> it.word)
                                        .distinct()
                                        .toList()
                        )),
                        // TODO for now we only return words which match stress syllables
                        // We should also return words which match the last one, two, or three syllables
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()
                )).toList();
    }
}