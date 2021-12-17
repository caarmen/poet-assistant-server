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

import ca.rmen.poetassistant.api.model.WotdApiModel;
import ca.rmen.poetassistant.api.model.mapping.WotdServiceModelExt;
import ca.rmen.poetassistant.service.WotdService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class WotdController {

    public static final String SERVICE = "/wotd";
    public static final String QUERY_PARAM_BEFORE = "before";
    public static final String QUERY_PARAM_SIZE = "size";
    private final WotdService service;

    public WotdController(WotdService service) {
        this.service = service;
    }

    @GetMapping(SERVICE)
    public List<WotdApiModel> getWotd(
            @RequestParam(value = QUERY_PARAM_BEFORE, required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Nullable
                    LocalDate before,

            @RequestParam(value = QUERY_PARAM_SIZE, defaultValue = "1")
            @Positive
            @Max(366)
                    int size
    ) {
        return service.findWotdEntries(Optional.ofNullable(before).orElse(LocalDate.now()), size)
                .stream()
                .map(WotdServiceModelExt::toApi)
                .toList();
    }
}
