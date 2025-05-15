package com.sulove.smartlywordsmart02.data

import com.sulove.smartlywordsmart02.Word

object SampleWordData {
    val words: ArrayList<Word>
        get() {
            val sampleWords = ArrayList<Word>()
            sampleWords.add(Word("Hello", "Used as a greeting"))
            sampleWords.add(Word("World", "The earth and all its people"))
            sampleWords.add(Word("Apple", "A fruit with a red or yellow skin and a rounded shape"))
            sampleWords.add(Word("Cat", "A small domesticated carnivorous mammal with soft fur, a short snout, and retractile claws"))
            sampleWords.add(Word("Sun", "The star around which the Earth orbits"))
            // Add more sample words as needed
            return sampleWords
        }
}
