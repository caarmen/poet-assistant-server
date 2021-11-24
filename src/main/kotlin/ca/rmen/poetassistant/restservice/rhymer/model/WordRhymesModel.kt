package ca.rmen.poetassistant.restservice.rhymer.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class WordRhymesModel(
    val variantNumber: Int,
    val stressRhymes: SyllableRhymesModel? = null,
    val lastThreeSyllableRhymes: SyllableRhymesModel? = null,
    val lastTwoSyllableRhymes: SyllableRhymesModel? = null,
    val lastSyllableRhymes: SyllableRhymesModel? = null
)