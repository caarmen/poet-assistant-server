# Poet Assistant Server

This server contains a read-only embedded database with dictionaries for rhymes, synonyms/antonyms and definitions.

It provides REST endpoints to look up the dictionary entries for a word.

The following REST endpoints are provided:

|Feature|Endpoint|
|---|---|
|Rhymes|`/rhymes?word=<word>`|
|Thesaurus|`/thesaurus?word=<word>`|
|Definitions|`/definition?word=<word>`|
|Words of the day|`/wotd?page=<page number>&size=<page size>`|

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
curl "http://localhost:8080/definition?word=happy"

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
### Words of the day

```shell
curl "http://localhost:8080/wotd?page=<page number>&size=<page size>"
```

For example, to find the words of the day for one week, ending one week ago:

```shell
curl "http://localhost:8080/wotd?page=1&size=7"
{"content":[{"date":"2021-11-20","word":"traitorous"},{"date":"2021-11-19","word":"unplug"},{"date":"2021-11-18","word":"glade"},{"date":"2021-11-17","word":"casework"},{"date":"2021-11-16","word":"replenish"},{"date":"2021-11-15","word":"eiderdown"},{"date":"2021-11-14","word":"oligarchic"}],"pageable":"INSTANCE","size":7,"number":0,"sort":{"empty":true,"sorted":false,"unsorted":true},"numberOfElements":7,"first":true,"last":true,"empty":false}
```

This response example in pretty print:
```json
{
  "content": [
    {
      "date": "2021-11-20",
      "word": "traitorous"
    },
    {
      "date": "2021-11-19",
      "word": "unplug"
    },
    {
      "date": "2021-11-18",
      "word": "glade"
    },
    {
      "date": "2021-11-17",
      "word": "casework"
    },
    {
      "date": "2021-11-16",
      "word": "replenish"
    },
    {
      "date": "2021-11-15",
      "word": "eiderdown"
    },
    {
      "date": "2021-11-14",
      "word": "oligarchic"
    }
  ],
  "pageable": "INSTANCE",
  "size": 7,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "numberOfElements": 7,
  "first": true,
  "last": true,
  "empty": false
}
```
