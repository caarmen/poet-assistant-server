package ca.rmen.poetassistant.restservice.rhymer.jpa

import org.springframework.data.repository.CrudRepository

interface RhymerRepository : CrudRepository<RhymerEntity, RhymerEntityPK> {
    fun findAllByWord(word: String): List<RhymerEntity>
    fun findAllByStressSyllablesAndWordNotOrderByWord(stressSyllables: String, word: String): List<RhymerEntity>

}