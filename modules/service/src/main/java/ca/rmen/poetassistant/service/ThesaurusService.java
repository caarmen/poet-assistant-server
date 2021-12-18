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

import ca.rmen.poetassistant.repository.thesaurus.ThesaurusRepository;
import ca.rmen.poetassistant.service.model.PartOfSpeechService;
import ca.rmen.poetassistant.service.model.ThesaurusEntryServiceModel;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ThesaurusService {
    private final ThesaurusRepository repository;

    public ThesaurusService(ThesaurusRepository repository) {
        this.repository = repository;
    }

    public List<ThesaurusEntryServiceModel> findThesaurusEntries(String word) {
        return repository.findAllByWord(word)
                .stream()
                .map((it) -> new ThesaurusEntryServiceModel(
                        toPartOfSpeech(it.wordType),
                        toWordList(it.synonyms),
                        toWordList(it.antonyms)
                ))
                .toList();
    }


    /**
     * In our database, the synonyms of a word are stored in a single
     * column, as a comma-separated list of words. We need to convert
     * this into a sorted list of words
     */
    private List<String> toWordList(String words) {
        return Arrays.stream(words.split(","))
                .filter((it) -> !it.isEmpty())
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .toList();
    }

    private PartOfSpeechService toPartOfSpeech(String partOfSpeech) {
        return switch (partOfSpeech) {
            case "ADJ" -> PartOfSpeechService.ADJECTIVE;
            case "NOUN" -> PartOfSpeechService.NOUN;
            case "ADV" -> PartOfSpeechService.ADVERB;
            case "VERB" -> PartOfSpeechService.VERB;
            default -> PartOfSpeechService.UNKNOWN;
        };
    }
}