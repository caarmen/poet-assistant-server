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

import ca.rmen.poetassistant.restservice.common.ResponseValidator.validateResultNotEmpty
import ca.rmen.poetassistant.restservice.definitions.model.DefinitionModel
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.NotBlank

@RestController
@Validated
class DefinitionController(private val service: DefinitionService) {
    companion object {
        const val SERVICE = "/definitions"
        const val QUERY_PARAM_WORD = "word"
    }

    @Operation(summary = "Find definitions for a word")
    @ApiResponses(
        ApiResponse(
            description = "The definitions of the given word", responseCode = "200",
            content = arrayOf(
                Content(
                    array = ArraySchema(
                        schema = Schema(implementation = DefinitionModel::class, name = "definition")
                    )
                )
            )
        ),
        ApiResponse(description = "No definitions exist for the word", responseCode = "404"),
        ApiResponse(description = "The given word is blank", responseCode = "400"),
    )
    @GetMapping(SERVICE)
    fun getDefinitions(@RequestParam(QUERY_PARAM_WORD) @NotBlank word: String): List<DefinitionModel> =
        service.findDefinitions(word.lowercase()).validateResultNotEmpty(word)
}
