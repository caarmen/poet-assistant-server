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
import ca.rmen.poetassistant.model.ThesaurusEntryModel
import ca.rmen.poetassistant.service.ThesaurusService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.{GetMapping, RequestParam, RestController}
import scala.collection.JavaConverters._

import javax.validation.constraints.NotBlank

@RestController
@Validated
class ThesaurusController(private val service: ThesaurusService) {
  @GetMapping(path = Array(ThesaurusController.SERVICE))
  def getThesaurusEntries(@RequestParam(ThesaurusController.QUERY_PARAM_WORD) @NotBlank word: String): java.util.List[ThesaurusEntryModel] =
    service.findThesaurusEntries(word.toLowerCase()).validateResultNotEmpty(word).asJava
}

object ThesaurusController {
  final val SERVICE = "/thesaurus"
  final val QUERY_PARAM_WORD = "word"
}
