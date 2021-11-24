package ca.rmen.poetassistant.restservice.definitions

import ca.rmen.poetassistant.restservice.definitions.jpa.DefinitionRepository
import ca.rmen.poetassistant.restservice.definitions.model.DefinitionModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DefinitionController {
    @Autowired
    private lateinit var repository: DefinitionRepository

    @GetMapping("/definition")
    fun definition(@RequestParam("word") word: String): ResponseEntity<List<DefinitionModel>> =
        repository.findAllByWord(word)
            .map {
                DefinitionModel(
                    word = it.word,
                    partOfSpeech = it.partOfSpeech,
                    definition = it.definition
                )
            }.takeIf { it.isNotEmpty() }
            ?.let { ResponseEntity.ok(it) } ?: ResponseEntity.status(HttpStatus.NOT_FOUND)
            .build()
}