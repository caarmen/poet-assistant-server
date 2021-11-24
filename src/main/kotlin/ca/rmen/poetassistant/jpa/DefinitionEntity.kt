package ca.rmen.poetassistant.jpa

import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

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