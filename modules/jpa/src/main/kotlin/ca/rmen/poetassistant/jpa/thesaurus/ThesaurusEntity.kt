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

package ca.rmen.poetassistant.jpa.thesaurus

import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Our embedded database doesn't have an id column.
 * So, we have to use all its attributes as the primary key
 */
@Entity
@Table(name = "thesaurus")
data class ThesaurusEntity(
    @EmbeddedId
    private val id: ThesaurusEntityPK = ThesaurusEntityPK(),
    @Column(insertable = false, updatable = false)
    val word: String = "",
    @Column(insertable = false, updatable = false)
    val wordType: String = "",
    @Column(insertable = false, updatable = false)
    val synonyms: String = "",
    @Column(insertable = false, updatable = false)
    val antonyms: String = "",
)