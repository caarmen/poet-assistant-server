package ca.rmen.poetassistant.restservice.rhymer.jpa

import java.io.Serializable
import javax.persistence.*

@Embeddable
data class RhymerEntityPK(
    val word: String = "",
    val variantNumber: Int = 0,
) : Serializable
