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

package ca.rmen.poetassistant.api;

import ca.rmen.poetassistant.api.model.ThesaurusEntryApiModel;
import ca.rmen.poetassistant.api.model.mapping.ThesaurusEntryServiceModelExt;
import ca.rmen.poetassistant.service.ThesaurusService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Locale;

@RestController
@Validated
public class ThesaurusController {

    public static final String SERVICE = "/thesaurus";
    public static final String QUERY_PARAM_WORD = "word";
    private final ThesaurusService service;

    public ThesaurusController(ThesaurusService service) {
        this.service = service;
    }

    @GetMapping(SERVICE)
    public List<ThesaurusEntryApiModel> getThesaurusEntries(@RequestParam(QUERY_PARAM_WORD) @NotBlank String word) {
        return ResponseValidator.validateResultNotEmpty(
                service.findThesaurusEntries(word.toLowerCase(Locale.ROOT))
                        .stream()
                        .map(ThesaurusEntryServiceModelExt::toApi)
                        .toList(),
                word
        );
    }
}
