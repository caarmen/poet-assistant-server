package ca.rmen.poetassistant.restservice.rhymer

import ca.rmen.poetassistant.restservice.rhymer.jpa.RhymerRepository
import ca.rmen.poetassistant.restservice.rhymer.model.SyllableRhymesModel
import ca.rmen.poetassistant.restservice.rhymer.model.WordRhymesModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RhymerController {
    @Autowired
    private lateinit var repository: RhymerRepository

    @GetMapping("/rhymes")
    fun definition(@RequestParam("word") word: String): List<WordRhymesModel> {
        val wordVariants = repository.findAllByWord(word)
        return wordVariants.map { wordVariant ->
            val wordVariantsMatchingStressSyllables = repository.findAllByStressSyllables(wordVariant.stressSyllables)
            WordRhymesModel(
                word = word,
                variantNumber = wordVariant.variantNumber,
                stressRhymes = SyllableRhymesModel(
                    syllables = wordVariant.stressSyllables,
                    rhymes = wordVariantsMatchingStressSyllables
                        .filter { it.word != word }
                        .map { it.word }
                        .sorted()
                )
            )
        }
    }
}
