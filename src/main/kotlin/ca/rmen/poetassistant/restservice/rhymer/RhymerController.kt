package ca.rmen.poetassistant.restservice.rhymer

import ca.rmen.poetassistant.restservice.rhymer.jpa.RhymerRepository
import ca.rmen.poetassistant.restservice.rhymer.model.SyllableRhymesModel
import ca.rmen.poetassistant.restservice.rhymer.model.WordRhymesModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RhymerController {
    @Autowired
    private lateinit var repository: RhymerRepository

    @GetMapping("/rhymes")
    fun rhymes(@RequestParam("word") word: String): ResponseEntity<List<WordRhymesModel>> =
        repository.findAllByWord(word).map { wordVariant ->
            WordRhymesModel(
                variantNumber = wordVariant.variantNumber,
                stressRhymes = SyllableRhymesModel(
                    syllables = wordVariant.stressSyllables,
                    rhymes = repository.findAllByStressSyllablesAndWordNotOrderByWord(wordVariant.stressSyllables, word)
                        .map { it.word }
                )
            )
        }.takeIf { it.isNotEmpty() }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    // TODO for now we only return words which match stress syllables
    // We should also return words which match the last one, two, or three syllables
}