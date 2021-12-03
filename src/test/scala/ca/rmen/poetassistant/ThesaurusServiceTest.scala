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

package ca.rmen.poetassistant

import ca.rmen.poetassistant.api.ThesaurusController
import ca.rmen.poetassistant.model.{PartOfSpeech, ThesaurusEntryModel}
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import scala.collection.JavaConverters.*

@SpringBootTest
class ThesaurusServiceTest {

  @Autowired
  private var controller: ThesaurusController = null

  @Test
  def testThesaurusEntriesOnlySynonyms() = {
    val actualThesaurusEntries = controller.getThesaurusEntries("nightmare").asScala.toList
    val expectedThesaurusEntries = List(
      ThesaurusEntryModel(
        partOfSpeech = PartOfSpeech.NOUN,
        synonyms = List("incubus", "situation"),
        antonyms = List()
      ),
      ThesaurusEntryModel(
        partOfSpeech = PartOfSpeech.NOUN,
        synonyms = List("dream", "dreaming"),
        antonyms = List()
      )
    )
    assertEquals(expectedThesaurusEntries, actualThesaurusEntries)
  }

  @Test
  def testThesaurusEntriesMixedSynonymsAndAntonyms() = {
    val actualThesaurusEntries = controller.getThesaurusEntries("unstructured").asScala.toList
    val expectedThesaurusEntries = List(
      ThesaurusEntryModel(
        partOfSpeech = PartOfSpeech.ADJECTIVE,
        synonyms = List("ambiguous", "unorganised", "unorganized", "unregulated"),
        antonyms = List("structured")
      ),
      ThesaurusEntryModel(
        partOfSpeech = PartOfSpeech.ADJECTIVE,
        synonyms = List("amorphous", "inorganic"),
        antonyms = List()
      )
    )
    assertEquals(expectedThesaurusEntries, actualThesaurusEntries)
  }
}
