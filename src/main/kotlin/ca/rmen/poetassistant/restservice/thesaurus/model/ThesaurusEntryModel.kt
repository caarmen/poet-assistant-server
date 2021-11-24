package ca.rmen.poetassistant.restservice.thesaurus.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ThesaurusEntryModel(
    val partOfSpeech: String,
    val synonyms: List<String>,
    val antonyms: List<String>
)