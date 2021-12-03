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

package ca.rmen.poetassistant.api

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.{ExceptionHandler, ResponseStatus, RestControllerAdvice}

import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ApplicationControllerAdvice {

  // Would like to not have to define this data class for the response body for constraint violations.
  // Would like to not have to define the message format at all for ConstraintViolationException.
  // If I only do this:
  //
  // @ExceptionHandler(ConstraintViolationException::class)
  // @ResponseStatus(HttpStatus.BAD_REQUEST)
  // fun onConstraintValidationException(e: ConstraintViolationException) {}
  //
  // I get a 400 response, but with no body.
  // If I make onConstraintViolationException return the exception, then:
  // - If I use @RestControllerAdvice, I get a 400 with a huge body containing the stacktrace
  // - If I use @ControllerAdvice, I get a 500 with a nice concise body with a few attributes
  // I didn't find a way to have a 400 status, with an automatically nice concise body.
  // So I create the body myself.  :shrug:

  case class ConstraintViolation(@JsonProperty("message") message: String)

  @ExceptionHandler(Array(classOf[ConstraintViolationException]))
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  def onConstraintViolationException(e: ConstraintViolationException) = ConstraintViolation(e.getLocalizedMessage)

}

