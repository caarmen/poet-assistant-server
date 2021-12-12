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

package ca.rmen.poetassistant.api

import ca.rmen.poetassistant.api.ResponseValidator.validateResultNotEmpty
import ca.rmen.poetassistant.api.model.ThesaurusEntryApiModel
import ca.rmen.poetassistant.api.model.mapping.ThesaurusEntryServiceModelExt._
import ca.rmen.poetassistant.service.ThesaurusService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.{GetMapping, RequestParam, RestController}

import javax.validation.constraints.NotBlank
import scala.collection.JavaConverters.*

@RestController
@Validated
class ThesaurusController(private val service: ThesaurusService) {
  @GetMapping(path = Array(ThesaurusController.SERVICE))
  def getThesaurusEntries(@RequestParam(ThesaurusController.QUERY_PARAM_WORD) @NotBlank word: String): java.util.List[ThesaurusEntryApiModel] =
    service.findThesaurusEntries(word.toLowerCase())
      .map(_.toThesaurusEntryApiModel())
      .validateResultNotEmpty(word).asJava
}

object ThesaurusController {
  final val SERVICE = "/thesaurus"
  final val QUERY_PARAM_WORD = "word"
}
