package ca.rmen.poetassistant.restservice.definitions

import ca.rmen.poetassistant.restservice.definitions.jpa.DefinitionRepository
import ca.rmen.poetassistant.restservice.definitions.model.DefinitionModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DefinitionController {
    @Autowired
    private lateinit var repository: DefinitionRepository

    @GetMapping("/definition")
    fun definition(@RequestParam("word") word: String): List<DefinitionModel> =
        repository.findAllByWord(word)
            .map { DefinitionModel(it.word, it.partOfSpeech, it.definition) }
}