package ca.rmen.poetassistant.jpa

import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Our embedded database doesn't have an id column.
 * A word may have multiple definition entries for a given part_of_speech value.
 * So, we have to use all three attributes as the primary key
 */
@Entity
@Table(name = "dictionary")
data class DefinitionEntity(
    @EmbeddedId
    private val id: DefinitionEntityPK = DefinitionEntityPK(),
    @Column(insertable = false, updatable = false)
    val word: String = "",
    @Column(insertable = false, updatable = false)
    val partOfSpeech: String = "",
    @Column(insertable = false, updatable = false)
    val definition: String = ""
)