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
```shell
curl "http://localhost:8080/rhymes?word=<word>"
````

For example, to find rhymes of the word "dove":
```shell
curl "http://localhost:8080/rhymes?word=dove"

[{"variant_number":0,"stress_rhymes":{"syllables":"AHV","rhymes":["above","belove","deneuve","deneuve","glove","gov","labov","labove","love","o'glove","of","of","shove","thereof","vanhove"]}},{"variant_number":1,"stress_rhymes":{"syllables":"OWV","rhymes":["boeve","bove","cove","drove","gove","grove","hove","labauve","nov","rove","soave","stove","strove","throve","tov","trove","wove"]}}]
```

This response example in pretty print:
```json
[
  {
    "variant_number": 0,
    "stress_rhymes": {
      "syllables": "AHV",
      "rhymes": [
        "above",
        "belove",
        "deneuve",
        "deneuve",
        "glove",
        "gov",
        "labov",
        "labove",
        "love",
        "o'glove",
        "of",
        "of",
        "shove",
        "thereof",
        "vanhove"
      ]
    }
  },
  {
    "variant_number": 1,
    "stress_rhymes": {
      "syllables": "OWV",
      "rhymes": [
        "boeve",
        "bove",
        "cove",
        "drove",
        "gove",
        "grove",
        "hove",
        "labauve",
        "nov",
        "rove",
        "soave",
        "stove",
        "strove",
        "throve",
        "tov",
        "trove",
        "wove"
      ]
    }
  }
]
```

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
