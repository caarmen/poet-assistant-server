package ca.rmen.poetassistant.restservice.definitions.jpa

import org.springframework.data.repository.CrudRepository

interface DefinitionRepository : CrudRepository<DefinitionEntity, DefinitionEntity> {
    fun findAllByWord(word: String) : List<DefinitionEntity>
}