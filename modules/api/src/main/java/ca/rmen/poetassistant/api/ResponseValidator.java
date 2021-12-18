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

package ca.rmen.poetassistant.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.NoSuchElementException;

public class ResponseValidator {

    public static <T> List<T> validateResultNotEmpty(List<T> list, String word) {
        if (list.isEmpty()) throw new EmptyResultException(word);
        return list;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class EmptyResultException extends NoSuchElementException {
        public EmptyResultException(String word) {
            super(String.format("No results found for %s", word));
        }
    }
}
