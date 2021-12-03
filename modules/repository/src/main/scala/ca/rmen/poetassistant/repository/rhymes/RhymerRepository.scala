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

import org.springframework.data.repository.CrudRepository

trait RhymerRepository extends CrudRepository[RhymerEntity, RhymerEntityPK] {
  def findAllByWord(word: String): java.util.List[RhymerEntity]

  // These long method names are possible thanks to jpa:
  // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
  def findByStressSyllablesAndWordNotOrderByWord(stressSyllables: String, word: String): java.util.List[RhymerEntity]

}
