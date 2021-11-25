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

package ca.rmen.poetassistant.restservice

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

object RequestValidator {
    fun validateInputNotBlank(name: String, value: String) {
        if (value.isBlank()) {
            throw EmptyInputException(name)
        }
    }

    fun validateResultNotEmpty(name: String, result: List<Any>) {
        if (result.isEmpty()) {
            throw EmptyResultException(name)
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    class EmptyInputException(name: String) : IllegalArgumentException("$name must not be empty")

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    class EmptyResultException(name: String) : IllegalArgumentException("No results found for $name")
}

