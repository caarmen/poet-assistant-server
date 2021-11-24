package ca.rmen.poetassistant.restservice.rhymer.jpa

import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "word_variants")
data class RhymerEntity(
    @EmbeddedId
    private val id: RhymerEntityPK = RhymerEntityPK(),
    @Column(insertable = false, updatable = false)
    val word: String = "",
    @Column(insertable = false, updatable = false)
    val variantNumber: Int = 0,
    @Column(insertable = false, updatable = false)
    val stressSyllables: String = "",
    @Column(insertable = false, updatable = false)
    val lastSyllable: String = "",
    @Column(insertable = false, updatable = false)
    val lastTwoSyllables: String = "",
    @Column(insertable = false, updatable = false)
    val lastThreeSyllables: String = "",
)