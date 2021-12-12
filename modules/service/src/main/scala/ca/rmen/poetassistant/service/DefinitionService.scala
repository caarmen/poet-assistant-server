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

import ca.rmen.poetassistant.repository.definitions.{DefinitionEntity, DefinitionRepository}
import ca.rmen.poetassistant.service.model.{DefinitionServiceModel, PartOfSpeechService}
import org.hibernate.hql.internal.ast.QueryTranslatorImpl.JavaConstantConverter
import org.springframework.stereotype.Service

import scala.collection.JavaConverters.*

@Service
class DefinitionService(repository: DefinitionRepository) {
  def findDefinitions(word: String): List[DefinitionServiceModel] =
    repository.findAllByWord(word).asScala.toList.map((entity: DefinitionEntity) =>
      DefinitionServiceModel(entity.partOfSpeech.toPartOfSpeech, entity.definition))

  extension (s: String)
    def toPartOfSpeech: PartOfSpeechService = s match {
      case "a" => PartOfSpeechService.ADJECTIVE
      case "n" => PartOfSpeechService.NOUN
      case "r" => PartOfSpeechService.ADVERB
      case "v" => PartOfSpeechService.VERB
      case _ => PartOfSpeechService.UNKNOWN
    }
}
