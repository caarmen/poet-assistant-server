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

package ca.rmen.poetassistant

import ca.rmen.poetassistant.api.WotdController
import ca.rmen.poetassistant.model.WotdModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import java.util.Optional
import java.time.LocalDate

@SpringBootTest
class WotdServiceTest() {

  @Autowired
  private var controller: WotdController = null

  @Test
  def testSingleWotd() = {
    // The expected words come from the wotd test of the iOS app:
    // https://github.com/caarmen/poet-assistant-ios/blob/master/PoetAssistantTests/WotdTest.swift
    assertWotd("2018-11-24", "vaccinate")
    assertWotd("2018-11-23", "devaluation")
    assertWotd("2018-11-22", "copulation")
    assertWotd("2018-11-21", "fuselage")
    assertWotd("2018-11-20", "lyricist")
    assertWotd("2018-11-19", "emphysema")
    assertWotd("2018-11-18", "auscultation")
    assertWotd("2018-11-17", "hypotonia")
    assertWotd("2018-11-16", "brigadier")
    assertWotd("2018-11-15", "gallstone")
    assertWotd("2018-11-14", "unspoilt")
    assertWotd("2018-11-13", "sprayer")
    assertWotd("2018-11-12", "tufa")
    assertWotd("2018-11-11", "braised")
    assertWotd("2018-11-10", "unselfishly")
    assertWotd("2018-11-09", "economise")
    assertWotd("2018-11-08", "navel")
    assertWotd("2018-11-07", "rhombus")
    assertWotd("2018-11-06", "swinish")
    assertWotd("2018-11-05", "offshoot")
    assertWotd("2018-11-04", "personation")
    assertWotd("2018-11-03", "sorrowing")
    assertWotd("2018-11-02", "leasehold")
    assertWotd("2018-11-01", "blowout")
    assertWotd("2018-10-31", "dropout")
    assertWotd("2018-10-30", "interrogatively")
    assertWotd("2018-10-29", "indelible")
    assertWotd("2018-10-28", "sprit")
    assertWotd("2018-10-27", "semiotic")
    assertWotd("2018-10-26", "normalisation")
    assertWotd("2018-10-25", "overladen")
    assertWotd("2018-10-24", "deist")
    assertWotd("2018-10-23", "silviculture")
    assertWotd("2018-10-22", "heathenism")
    assertWotd("2018-10-21", "divisive")
    assertWotd("2018-10-20", "unripe")
    assertWotd("2018-10-19", "soviet")
    assertWotd("2018-10-18", "napped")
    assertWotd("2018-10-17", "minder")
    assertWotd("2018-10-16", "heighten")
    assertWotd("2018-10-15", "karat")
    assertWotd("2018-10-14", "phosphorous")
    assertWotd("2018-10-13", "firearm")
    assertWotd("2018-10-12", "hypotenuse")
    assertWotd("2018-10-11", "boldface")
    assertWotd("2018-10-10", "recapitulate")
    assertWotd("2018-10-09", "alchemist")
    assertWotd("2018-10-08", "abrogate")
    assertWotd("2018-10-07", "binnacle")
    assertWotd("2018-10-06", "unimpressive")
    assertWotd("2018-10-05", "insanitary")
    assertWotd("2018-10-04", "friday")
    assertWotd("2018-10-03", "ancestress")
    assertWotd("2018-10-02", "badminton")
    assertWotd("2018-10-01", "bioremediation")
    assertWotd("2018-09-30", "servo")
    assertWotd("2018-09-29", "mobilisation")
    assertWotd("2018-09-28", "taproot")
    assertWotd("2018-09-27", "relinquishing")
    assertWotd("2018-09-26", "creosote")
    assertWotd("2018-09-25", "autograph")
    assertWotd("2018-09-24", "catechetical")
    assertWotd("2018-09-23", "jib")
    assertWotd("2018-09-22", "protraction")
    assertWotd("2018-09-21", "ambit")
    assertWotd("2018-09-20", "panchayat")
    assertWotd("2018-09-19", "deb")
    assertWotd("2018-09-18", "territorially")
    assertWotd("2018-09-17", "hart")
    assertWotd("2018-09-16", "downtrodden")
    assertWotd("2018-09-15", "prolapse")
    assertWotd("2018-09-14", "metaphysically")
    assertWotd("2018-09-13", "substratum")
    assertWotd("2018-09-12", "adroitly")
    assertWotd("2018-09-11", "isi")
    assertWotd("2018-09-10", "yardarm")
    assertWotd("2018-09-09", "pullout")
    assertWotd("2018-09-08", "computationally")
    assertWotd("2018-09-07", "schoolyard")
    assertWotd("2018-09-06", "advisedly")
    assertWotd("2018-09-05", "maxillofacial")
    assertWotd("2018-09-04", "belike")
    assertWotd("2018-09-03", "storyteller")
    assertWotd("2018-09-02", "blip")
    assertWotd("2018-09-01", "colorist")
    assertWotd("2018-08-31", "scythe")
    assertWotd("2018-08-30", "timbered")
    assertWotd("2018-08-29", "overspread")
    assertWotd("2018-08-28", "succinct")
    assertWotd("2018-08-27", "masochistic")
    assertWotd("2018-08-26", "maltreatment")
    assertWotd("2018-08-25", "dampen")
    assertWotd("2018-08-24", "bichromate")
    assertWotd("2018-08-23", "subluxation")
    assertWotd("2018-08-22", "prodigiously")
    assertWotd("2018-08-21", "superficiality")
    assertWotd("2018-08-20", "territorially")
    assertWotd("2018-08-19", "unconstrained")
    assertWotd("2018-08-18", "literati")
    assertWotd("2018-08-17", "dispossessed")
  }

  private def assertWotd(dateString: String, expectedWotd: String) = {
    val date = LocalDate.parse(dateString)
    val actualWotdList = controller.getWotd(Optional.of(date), 1)
    assertEquals(1, actualWotdList.size)
    assertEquals(date, actualWotdList.head.date)
    assertEquals(expectedWotd, actualWotdList.head.word)
  }

  @Test
  def testListWotd() = {
    val date = LocalDate.parse("2021-11-28")
    val expectedWotdList = List(
      WotdModel(LocalDate.parse("2021-11-28"), "clothesline"),
      WotdModel(LocalDate.parse("2021-11-27"), "solstice"),
      WotdModel(LocalDate.parse("2021-11-26"), "methylphenidate")
    )
    val actualWotdList = controller.getWotd(Optional.of(date), 3)
    assertEquals(expectedWotdList, actualWotdList)
  }

}
