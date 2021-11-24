package ca.rmen.poetassistant.restservice.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class DefinitionModel(
    val word: String,
    val partOfSpeech: String,
    val definition: String
)