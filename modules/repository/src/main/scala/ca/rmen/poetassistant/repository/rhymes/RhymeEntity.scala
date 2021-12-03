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

package ca.rmen.poetassistant.repository.rhymes

import javax.persistence.{Column, EmbeddedId, Entity, Table}
import scala.beans.BeanProperty

@Entity
@Table(name = "word_variants")
class RhymerEntity {
  @EmbeddedId
  var id: RhymerEntityPK = new RhymerEntityPK()
  @Column(insertable = false, updatable = false)
  var word: String = ""
  @Column(insertable = false, updatable = false)
  var variantNumber: Int = 0
  @Column(insertable = false, updatable = false)
  var stressSyllables: String = ""
  @Column(insertable = false, updatable = false)
  var lastSyllable: String = ""
  @Column(insertable = false, updatable = false)
  var lastTwoSyllables: String = ""
  @Column(insertable = false, updatable = false)
  var lastThreeSyllables: String = ""
}
