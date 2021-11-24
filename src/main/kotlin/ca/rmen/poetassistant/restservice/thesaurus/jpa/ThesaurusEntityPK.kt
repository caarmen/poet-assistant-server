package ca.rmen.poetassistant.restservice.thesaurus.jpa

import java.io.Serializable
import javax.persistence.*

@Embeddable
data class ThesaurusEntityPK(
    val word: String = "",
    val wordType: String = "",
    val synonyms: String = "",
    val antonyms: String = ""
) : Serializable
