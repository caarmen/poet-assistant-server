package ca.rmen.poetassistant.restservice.thesaurus.jpa

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