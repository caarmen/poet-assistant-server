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

import ca.rmen.poetassistant.restservice.definitions.DefinitionController
import ca.rmen.poetassistant.restservice.definitions.model.DefinitionModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DefinitionTests {

    @Autowired
    private lateinit var controller: DefinitionController

    @Test
    fun testDefinitions() {
        val actualDefinitions = controller.getDefinitions("happy")
        val expectedDefinitions = listOf(
            DefinitionModel(
                partOfSpeech = "a",
                definition = "eagerly disposed to act or to be of service"
            ),
            DefinitionModel(
                partOfSpeech = "a",
                definition = "enjoying or showing or marked by joy or pleasure"
            ),
            DefinitionModel(
                partOfSpeech = "a",
                definition = "marked by good fortune"
            ),
            DefinitionModel(
                partOfSpeech = "a",
                definition = "well expressed and to the point"
            )
        )
        assertEquals(expectedDefinitions, actualDefinitions)
    }
}
