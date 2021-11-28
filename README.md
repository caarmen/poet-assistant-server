# Poet Assistant Server

This server contains a read-only embedded database with dictionaries for rhymes, synonyms/antonyms and definitions.

It provides REST endpoints to look up the dictionary entries for a word.

The following REST endpoints are provided:

|Feature|Endpoint|
|---|---|
|Rhymes|`/rhymes?word=<word>`|
|Thesaurus|`/thesaurus?word=<word>`|
|Definitions|`/definitions?word=<word>`|
|Words of the day|`/wotd?before=<yyyy-MM-dd>&size=<page size>`<br>`before`: inclusive, defaults to today<br>`size`: defaults to 1|

## How to use it
Start the application:
```shell
./gradlew bootRun
```

### Rhymes
```shell
curl "http://localhost:8080/rhymes?word=<word>"
````

For example, to find rhymes of the word "dove":
```shell
curl "http://localhost:8080/rhymes?word=dove"

[{"variant_number":0,"stress_rhymes":{"syllables":"AHV","rhymes":["above","belove","deneuve","glove","gov","labov","labove","love","o'glove","of","shove","thereof","vanhove"]}},{"variant_number":1,"stress_rhymes":{"syllables":"OWV","rhymes":["boeve","bove","cove","drove","gove","grove","hove","labauve","nov","rove","soave","stove","strove","throve","tov","trove","wove"]}}]
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
        "glove",
        "gov",
        "labov",
        "labove",
        "love",
        "o'glove",
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
curl "http://localhost:8080/thesaurus?word=happy"

[{"part_of_speech":"ADJ","synonyms":["blessed","blissful","bright","cheerful","content","contented","elated","euphoric","felicitous","glad","golden","halcyon","joyful","joyous","laughing","prosperous","riant"],"antonyms":["unhappy"]},{"part_of_speech":"ADJ","synonyms":["felicitous","fortunate"],"antonyms":[]},{"part_of_speech":"ADJ","synonyms":["glad","willing"],"antonyms":[]},{"part_of_speech":"ADJ","synonyms":["felicitous","well-chosen"],"antonyms":[]}]%
```

This response example in pretty print:
```json
[
  {
    "part_of_speech": "ADJ",
    "synonyms": [
      "blessed",
      "blissful",
      "bright",
      "cheerful",
      "content",
      "contented",
      "elated",
      "euphoric",
      "felicitous",
      "glad",
      "golden",
      "halcyon",
      "joyful",
      "joyous",
      "laughing",
      "prosperous",
      "riant"
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
      "glad",
      "willing"
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
curl "http://localhost:8080/definitions?word=<word>"
```

For example, to find definitions of the word "happy":

```shell
curl "http://localhost:8080/definitions?word=happy"
[{"part_of_speech":"a","definition":"eagerly disposed to act or to be of service"},{"part_of_speech":"a","definition":"enjoying or showing or marked by joy or pleasure"},{"part_of_speech":"a","definition":"marked by good fortune"},{"part_of_speech":"a","definition":"well expressed and to the point"}]  
```

This response example in pretty print:
```json
[
  {
    "part_of_speech": "a",
    "definition": "eagerly disposed to act or to be of service"
  },
  {
    "part_of_speech": "a",
    "definition": "enjoying or showing or marked by joy or pleasure"
  },
  {
    "part_of_speech": "a",
    "definition": "marked by good fortune"
  },
  {
    "part_of_speech": "a",
    "definition": "well expressed and to the point"
  }
]
```
### Words of the day

```shell
curl "http://localhost:8080/wotd?before=yyyy-MM-dd&size=<page size>"
```

To find to word of the day for today:
```shell
curl -i "http://localhost:8080/wotd" 
```

```json
[{"date":"2021-11-28","word":"clothesline"}]
```

To find the word of the day for last week of 2021:
```shell
curl -i "http://localhost:8080/wotd?before=2021-12-31&size=7"
```

```json
[{"date":"2021-12-31","word":"raindrop"},{"date":"2021-12-30","word":"ceaselessly"},{"date":"2021-12-29","word":"foresail"},{"date":"2021-12-28","word":"haematopoietic"},{"date":"2021-12-27","word":"crosier"},{"date":"2021-12-26","word":"demo"},{"date":"2021-12-25","word":"ceaselessly"}
```

This response example in pretty print:
```json
[
  {
    "date": "2021-12-31",
    "word": "raindrop"
  },
  {
    "date": "2021-12-30",
    "word": "ceaselessly"
  },
  {
    "date": "2021-12-29",
    "word": "foresail"
  },
  {
    "date": "2021-12-28",
    "word": "haematopoietic"
  },
  {
    "date": "2021-12-27",
    "word": "crosier"
  },
  {
    "date": "2021-12-26",
    "word": "demo"
  },
  {
    "date": "2021-12-25",
    "word": "ceaselessly"
  }
]
```
