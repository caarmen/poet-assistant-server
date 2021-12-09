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

package ca.rmen.poetassistant.service.model

import com.fasterxml.jackson.annotation.JsonProperty

data class WordRhymesServiceModel(
    @JsonProperty("variant_number")
    val variantNumber: Int,
    @JsonProperty("stress_rhymes")
    val stressRhymes: SyllableRhymesServiceModel? = null,
    @JsonProperty("last_three_syllable_rhymes")
    val lastThreeSyllableRhymes: SyllableRhymesServiceModel? = null,
    @JsonProperty("last_two_syllable_rhymes")
    val lastTwoSyllableRhymes: SyllableRhymesServiceModel? = null,
    @JsonProperty("last_syllable_rhymes")
    val lastSyllableRhymes: SyllableRhymesServiceModel? = null
)