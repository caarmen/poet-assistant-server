package ca.rmen.poetassistant.restservice.rhymer.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class SyllableRhymesModel(
    val syllables: String,
    val rhymes: List<String>
)