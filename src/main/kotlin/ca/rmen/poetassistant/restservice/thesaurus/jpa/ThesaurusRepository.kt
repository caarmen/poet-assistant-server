package ca.rmen.poetassistant.restservice.thesaurus.jpa

import org.springframework.data.repository.CrudRepository

interface ThesaurusRepository : CrudRepository<ThesaurusEntity, ThesaurusEntityPK> {
    fun findAllByWord(word: String): List<ThesaurusEntity>
}