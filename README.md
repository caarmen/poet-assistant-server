# Poet Assistant Server

This server contains a read-only embedded database with dictionaries for rhymes, synonyms/antonyms and definitions.

It provides REST endpoints to look up the dictionary entries for a word.

So far, only the definition dictionary is provided.

## How to use it
Start the application:
```shell
./gradlew bootRun
```

Use the application:

### Rhymes
Not implemented yet

### Thesaurus
```shell
curl "http://localhost:8080/thesaurus?word=<word>"
```

For example, to find thesaurus entries of the word "happy":
```shell
curl "http://localhost:8080/definition?word=happy"

[{"word":"happy","part_of_speech":"a","definition":"eagerly disposed to act or to be of service"},{"word":"happy","part_of_speech":"a","definition":"enjoying or showing or marked by joy or pleasure"},{"word":"happy","part_of_speech":"a","definition":"marked by good fortune"},{"word":"happy","part_of_speech":"a","definition":"well expressed and to the point"}]
```

This response example in pretty print:
```json
[
  {
    "part_of_speech": "ADJ",
    "synonyms": [
      "halcyon",
      "content",
      "blessed",
      "contented",
      "felicitous",
      "joyous",
      "blissful",
      "joyful",
      "riant",
      "prosperous",
      "laughing",
      "cheerful",
      "euphoric",
      "golden",
      "elated",
      "bright",
      "glad"
    ],
    "antonyms": [
      "unhappy"
    ]
  },
  {
    "part_of_speech": "ADJ",
    "synonyms": [
      "felicitous",
      "fortunate"
    ],
    "antonyms": []
  },
  {
    "part_of_speech": "ADJ",
    "synonyms": [
      "willing",
      "glad"
    ],
    "antonyms": []
  },
  {
    "part_of_speech": "ADJ",
    "synonyms": [
      "felicitous",
      "well-chosen"
    ],
    "antonyms": []
  }
]
```

### Definitions

```shell
curl "http://localhost:8080/definition?word=<word>"
```

For example, to find definitions of the word "happy":

```shell
curl "http://localhost:8080/definition?word=happy"

[{"word":"happy","part_of_speech":"a","definition":"eagerly disposed to act or to be of service"},{"word":"happy","part_of_speech":"a","definition":"enjoying or showing or marked by joy or pleasure"},{"word":"happy","part_of_speech":"a","definition":"marked by good fortune"},{"word":"happy","part_of_speech":"a","definition":"well expressed and to the point"}]
```

This response example in pretty print:
```json
[
  {
    "word": "happy",
    "part_of_speech": "a",
    "definition": "eagerly disposed to act or to be of service"
  },
  {
    "word": "happy",
    "part_of_speech": "a",
    "definition": "enjoying or showing or marked by joy or pleasure"
  },
  {
    "word": "happy",
    "part_of_speech": "a",
    "definition": "marked by good fortune"
  },
  {
    "word": "happy",
    "part_of_speech": "a",
    "definition": "well expressed and to the point"
  }
]
```
