package ca.rmen.poetassistant.restservice.thesaurus

import ca.rmen.poetassistant.restservice.thesaurus.jpa.ThesaurusRepository
import ca.rmen.poetassistant.restservice.thesaurus.model.ThesaurusEntryModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ThesaurusController {
    @Autowired
    private lateinit var repository: ThesaurusRepository

    @GetMapping("/thesaurus")
    fun thesaurus(@RequestParam("word") word: String): List<ThesaurusEntryModel> =
        repository.findAllByWord(word)
            .map {
                ThesaurusEntryModel(
                    partOfSpeech = it.wordType,
                    synonyms = it.synonyms.split(",").filter { synonym -> synonym.isNotEmpty() },
                    antonyms = it.antonyms.split(",").filter { antonym -> antonym.isNotEmpty() },
                )
            }
}