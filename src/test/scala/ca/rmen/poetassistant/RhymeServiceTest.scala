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

import ca.rmen.poetassistant.api.RhymeController
import ca.rmen.poetassistant.api.model.{SyllableRhymesApiModel, WordRhymesApiModel}
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RhymeServiceTest {

  @Autowired
  private var controller: RhymeController = null

  @Test
  def testRhymes() = {
    val actualRhymes = controller.getRhymes("happy")
    val expectedRhymes = List(
      WordRhymesApiModel(
        variantNumber = 0, stressRhymes = Option(
          SyllableRhymesApiModel(
            syllables = "AEPIY",
            rhymes = List(
              "cappy",
              "chappie",
              "crappie",
              "crappy",
              "mapi",
              "nappi",
              "scrappy",
              "slappey",
              "snappy",
              "tapie",
              "unhappy"
            )
          )
        )
      )
    )
    assertEquals(expectedRhymes, actualRhymes)
  }
}
