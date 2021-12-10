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

package ca.rmen.poetassistant.service

import ca.rmen.poetassistant.repository.wotd.StemEntity
import ca.rmen.poetassistant.repository.wotd.StemRepository
import ca.rmen.poetassistant.service.model.WotdServiceModel
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Random

@Service
class WotdService(private val repository: StemRepository) {
    companion object {
        // When looking up random words, their "frequency" is a factor in the selection.
        // Words which are too frequent (a, the, why) are not interesting words.
        // Words which are too rare (aalto) are likely not interesting either.

        // Note: we want the words with frequency between 1500 and 25000 exclusive, to
        // return the same values as in the android/ios implementations.
        // But here, we take advantage of the Jpa/jpql "between" which is inclusive.
        // So we adjust the bounds by 1.
        private const val MIN_INTERESTING_FREQUENCY = 1501
        private const val MAX_INTERESTING_FREQUENCY = 24999
    }

    // Comment on performance:
    // This will load all the "interesting words" into memory. There are
    // about 20000 entries. In case this could be a performance issue, I
    // wanted to use other apis (for example, prepareStatement with
    // ResultSet), which could iterate to the "random" positions in the db,
    // "size" times, to create the number of objects we need for the result.
    // It appears, however, that the jdbc driver for sqlite only allows
    // moving forward, row by row.
    // Load the list of words in memory just once. The data won't change, and
    // this will help us to provide a response more quickly after the first
    // request
    private val interestingWords: List<StemEntity> by lazy {
        repository.findByGoogleNgramFrequencyBetween(MIN_INTERESTING_FREQUENCY, MAX_INTERESTING_FREQUENCY)
    }

    fun findWotdEntries(before: LocalDate, size: Int): List<WotdServiceModel> =
        (0 until size).map { resultPosition ->
            val dateForWotd = before.minusDays(resultPosition.toLong())
            val positionForDate = Random().apply {
                setSeed(dateForWotd.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli())
            }.nextInt(interestingWords.size)
            WotdServiceModel(
                date = dateForWotd,
                word = interestingWords[positionForDate].word,
            )
        }
}
