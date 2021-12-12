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

package ca.rmen.poetassistant.api.model.mapping

import ca.rmen.poetassistant.api.model.mapping.ServiceModelExt._
import ca.rmen.poetassistant.api.model.{DefinitionApiModel, PartOfSpeechApi, SyllableRhymesApiModel, ThesaurusEntryApiModel, WordRhymesApiModel, WotdApiModel}
import ca.rmen.poetassistant.service.model.{DefinitionServiceModel, PartOfSpeechService, SyllableRhymesServiceModel, ThesaurusEntryServiceModel, WordRhymesServiceModel, WotdServiceModel}

object ServiceModelExt {

  extension (x: DefinitionServiceModel) def toApi: DefinitionApiModel = {
    DefinitionApiModel(
      partOfSpeech = x.partOfSpeech.toApi,
      definition = x.definition
    )
  }
  extension (x: PartOfSpeechService) {
    def toApi : PartOfSpeechApi = {
      x match {
        case PartOfSpeechService.VERB => PartOfSpeechApi.VERB
        case PartOfSpeechService.NOUN => PartOfSpeechApi.NOUN
        case PartOfSpeechService.ADVERB => PartOfSpeechApi.ADVERB
        case PartOfSpeechService.ADJECTIVE => PartOfSpeechApi.ADJECTIVE
        case PartOfSpeechService.UNKNOWN => PartOfSpeechApi.UNKNOWN
      }
    }
  }
  extension (x: SyllableRhymesServiceModel) def toApi: SyllableRhymesApiModel = SyllableRhymesApiModel(
    syllables = x.syllables,
    rhymes = x.rhymes
  )
  extension (x: Option[SyllableRhymesServiceModel]) def toApi: Option[SyllableRhymesApiModel] = {
    x.map(_.toApi)
  }
  extension (x: ThesaurusEntryServiceModel) def toApi : ThesaurusEntryApiModel = ThesaurusEntryApiModel(
    partOfSpeech = x.partOfSpeech.toApi,
    synonyms = x.synonyms,
    antonyms = x.antonyms
  )
  extension (x: WordRhymesServiceModel) def toApi : WordRhymesApiModel = WordRhymesApiModel(
    variantNumber = x.variantNumber,
    stressRhymes = x.stressRhymes.toApi,
    lastThreeSyllableRhymes = x.lastThreeSyllableRhymes.toApi,
    lastTwoSyllableRhymes = x.lastTwoSyllableRhymes.toApi,
    lastSyllableRhymes = x.lastSyllableRhymes.toApi
  )
  extension (x: WotdServiceModel) def toApi : WotdApiModel = WotdApiModel(
    date = x.date,
    word = x.word
  )
}
