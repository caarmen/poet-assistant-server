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

package ca.rmen.poetassistant.restservice.wotd

import ca.rmen.poetassistant.restservice.wotd.model.WotdModel
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import javax.validation.constraints.Max
import javax.validation.constraints.Positive

@RestController
@Validated
class WotdController(private val service: WotdService) {

    companion object {
        const val SERVICE = "/wotd"
        const val QUERY_PARAM_BEFORE = "before"
        const val QUERY_PARAM_SIZE = "size"
    }

    @GetMapping(SERVICE)
    fun getWotd(
        @RequestParam(QUERY_PARAM_BEFORE, required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        before: LocalDate?,

        @RequestParam(QUERY_PARAM_SIZE, defaultValue = "1")
        @Positive
        @Max(366)
        size: Int
    ): List<WotdModel> = service.findWotdEntries(before ?: LocalDate.now(), size)
}
