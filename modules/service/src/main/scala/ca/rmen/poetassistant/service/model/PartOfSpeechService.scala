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

package ca.rmen.poetassistant.service.model

// The `JsonValue` annotation doesn't work:
// https://github.com/FasterXML/jackson-module-scala/issues/532
enum PartOfSpeechService(val modelName: String):

  case NOUN extends PartOfSpeechService("noun")
  case ADJECTIVE extends PartOfSpeechService("adjective")
  case ADVERB extends PartOfSpeechService("adverb")
  case VERB extends PartOfSpeechService("verb")
  case UNKNOWN extends PartOfSpeechService("unknown")

  override def toString: String = modelName
end PartOfSpeechService
