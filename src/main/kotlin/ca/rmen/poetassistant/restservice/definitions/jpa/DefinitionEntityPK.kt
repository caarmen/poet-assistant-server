package ca.rmen.poetassistant.restservice.definitions.jpa

import java.io.Serializable
import javax.persistence.*

@Embeddable
data class DefinitionEntityPK(
    val word: String = "",
    val partOfSpeech: String = "",
    val definition: String = ""
) : Serializable
