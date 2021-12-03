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

import ca.rmen.poetassistant.model.{PartOfSpeech, ThesaurusEntryModel}
import ca.rmen.poetassistant.repository.thesaurus.{ThesaurusEntity, ThesaurusRepository}
import org.springframework.stereotype.Service
import scala.collection.JavaConverters._

@Service
class ThesaurusService(repository: ThesaurusRepository) {

  def findThesaurusEntries(word: String): List[ThesaurusEntryModel] =
    repository.findAllByWord(word).asScala.toList
      .map((entity: ThesaurusEntity) =>
        ThesaurusEntryModel(
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
    def toPartOfSpeech: PartOfSpeech = s match {
      case "ADJ" => PartOfSpeech.ADJECTIVE
      case "NOUN" => PartOfSpeech.NOUN
      case "ADV" => PartOfSpeech.ADVERB
      case "VERB" => PartOfSpeech.VERB
      case _ => PartOfSpeech.UNKNOWN
    }
}
