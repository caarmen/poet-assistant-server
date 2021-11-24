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

package ca.rmen.poetassistant.restservice.rhymer.jpa

import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "word_variants")
data class RhymerEntity(
    @EmbeddedId
    private val id: RhymerEntityPK = RhymerEntityPK(),
    @Column(insertable = false, updatable = false)
    val word: String = "",
    @Column(insertable = false, updatable = false)
    val variantNumber: Int = 0,
    @Column(insertable = false, updatable = false)
    val stressSyllables: String = "",
    @Column(insertable = false, updatable = false)
    val lastSyllable: String = "",
    @Column(insertable = false, updatable = false)
    val lastTwoSyllables: String = "",
    @Column(insertable = false, updatable = false)
    val lastThreeSyllables: String = "",
)