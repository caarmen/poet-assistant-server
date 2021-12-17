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

package ca.rmen.poetassistant;

import ca.rmen.poetassistant.api.DefinitionController;
import ca.rmen.poetassistant.api.model.DefinitionApiModel;
import ca.rmen.poetassistant.api.model.PartOfSpeechApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DefinitionServiceTest {
    @Autowired
    private DefinitionController controller;

    @Test
    void testDefinitions() {
        var actualDefinitions = controller.getDefinitions("happy");
        var expectedDefinitions = List.of(
                new DefinitionApiModel(
                        PartOfSpeechApi.ADJECTIVE,
                        "eagerly disposed to act or to be of service"
                ),
                new DefinitionApiModel(
                        PartOfSpeechApi.ADJECTIVE,
                        "enjoying or showing or marked by joy or pleasure"
                ),
                new DefinitionApiModel(
                        PartOfSpeechApi.ADJECTIVE,
                        "marked by good fortune"
                ),
                new DefinitionApiModel(
                        PartOfSpeechApi.ADJECTIVE,
                        "well expressed and to the point"
                )
        );
        assertEquals(expectedDefinitions, actualDefinitions);
    }
}
