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

import ca.rmen.poetassistant.api.DefinitionController
import ca.rmen.poetassistant.api.model.DefinitionApiModel
import ca.rmen.poetassistant.api.model.PartOfSpeechApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DefinitionServiceTest() {

  @Autowired
  var controller: DefinitionController = null

  @Test
  def testDefinitions() = {
    val actualDefinitions = controller.getDefinitions("happy")
    val expectedDefinitions = List(
      DefinitionApiModel(
        partOfSpeech = PartOfSpeechApi.ADJECTIVE,
        definition = "eagerly disposed to act or to be of service"
      ),
      DefinitionApiModel(
        partOfSpeech = PartOfSpeechApi.ADJECTIVE,
        definition = "enjoying or showing or marked by joy or pleasure"
      ),
      DefinitionApiModel(
        partOfSpeech = PartOfSpeechApi.ADJECTIVE,
        definition = "marked by good fortune"
      ),
      DefinitionApiModel(
        partOfSpeech = PartOfSpeechApi.ADJECTIVE,
        definition = "well expressed and to the point"
      )
    )
    assertEquals(expectedDefinitions, actualDefinitions)
  }
}
