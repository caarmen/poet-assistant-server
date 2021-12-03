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

import ca.rmen.poetassistant.model.WotdModel
import ca.rmen.poetassistant.service.WotdService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.{GetMapping, RequestParam, RestController}

import java.time.LocalDate
import javax.validation.constraints.{Max, Positive}

@RestController
@Validated
class WotdController(private val service: WotdService) {

  @GetMapping(path = Array(WotdController.SERVICE))
  def getWotd(
               @RequestParam(WotdController.QUERY_PARAM_BEFORE, required = false)
               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
               before: Option[LocalDate],

               @RequestParam(WotdController.QUERY_PARAM_SIZE, defaultValue = "1")
               @Positive
               @Max(value = 366L)
               size: Int
             ):
  List[WotdModel] = service.findWotdEntries(before.getOrElse(LocalDate.now), size)
}

object WotdController {
  final val SERVICE = "/wotd"
  final val QUERY_PARAM_BEFORE = "before"
  final val QUERY_PARAM_SIZE = "size"

}