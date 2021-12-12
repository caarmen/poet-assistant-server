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

import ca.rmen.poetassistant.repository.thesaurus.{ThesaurusEntity, ThesaurusRepository}
import ca.rmen.poetassistant.service.model.{PartOfSpeechService, ThesaurusEntryServiceModel}
import org.springframework.stereotype.Service

import scala.collection.JavaConverters.*

@Service
class ThesaurusService(repository: ThesaurusRepository) {

  def findThesaurusEntries(word: String): List[ThesaurusEntryServiceModel] =
    repository.findAllByWord(word).asScala.toList
      .map((entity: ThesaurusEntity) =>
        ThesaurusEntryServiceModel(
          partOfSpeech = entity.wordType.toPartOfSpeech,
          synonyms = entity.synonyms.toWordList,
          antonyms = entity.antonyms.toWordList
        )
      )

  /**
   * In our database, the synonyms of a word are stored in a single
   * column, as a comma-separated list of words. We need to convert
   * this into a sorted list of words
   */
  extension (s: String)
    def toWordList: List[String] = s.split(",")
      .filter(_.nonEmpty)
      .sortBy(_.toLowerCase())
      .toList

  extension (s: String)
    def toPartOfSpeech: PartOfSpeechService = s match {
      case "ADJ" => PartOfSpeechService.ADJECTIVE
      case "NOUN" => PartOfSpeechService.NOUN
      case "ADV" => PartOfSpeechService.ADVERB
      case "VERB" => PartOfSpeechService.VERB
      case _ => PartOfSpeechService.UNKNOWN
    }
}
