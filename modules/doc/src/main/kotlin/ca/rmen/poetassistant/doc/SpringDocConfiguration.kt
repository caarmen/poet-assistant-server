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

package ca.rmen.poetassistant.doc

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringDocConfiguration {
    @Bean
    fun springOpenAPI() = OpenAPI()
        .info(
            Info().title("Poet Assistant REST Api")
                .description("English-language tools for writing poetry")
                .license(
                    License().name("GPLv3")
                        .url("https://github.com/caarmen/poet-assistant-server/blob/main/LICENSE.txt")
                )
        )
        .externalDocs(
            ExternalDocumentation()
                .description("Poet Assistant Documentation")
                .url("https://caarmen.github.io/poet-assistant-server/")
        )
}
