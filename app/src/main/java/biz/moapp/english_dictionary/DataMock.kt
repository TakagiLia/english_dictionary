package biz.moapp.english_dictionary

/**DataMock**/
object DataMock {
    data class EnglishSynonym(val eng: String, val jp: String)

    val japaneseMeanings = listOf(
        "リンゴ",
        "リンゴの木",
        "(俗)爆弾",
        "(米俗)人",
        "(the apple of one's eye) (人の)掌中の玉、愛しい者"
    )

    val englishExampleSentences = listOf(
        "I eat an apple every day.",
        "We planted an apple tree in the garden.",
        "The apples turn red in autumn.",
        "She baked an apple pie for dessert.",
        "He took a bite of the juicy apple.",
        "The apple fell from the tree with a thud.",
        "They went apple picking in the orchard.",
        "Would you like some apple juice?",
        "The teacher was the apple of her students' eyes.",
        "Don't upset the apple cart by changing the plan at the last minute."
    )

    val englishSynonyms = listOf(
        EnglishSynonym("Malus pumila", "リンゴ属の植物"),
        EnglishSynonym("fruit", "果物"),
        EnglishSynonym("pome", "仁果"),
        EnglishSynonym("the Big Apple", "ニューヨーク市の愛称"),
        EnglishSynonym("Adam's apple", "のどぼとけ")
    )

    val englishCoOccurrences = listOf(
        "eat an apple",
        "plant an apple tree",
        "go apple picking",
        "drink apple juice",
        "make apple pie",
        "peel an apple",
        "upset the apple cart (計画などをぶち壊す)",
        "apple of discord (争いの種)",
        "apple polisher (ご機嫌取り)",
        "rotten apple (グループの中のたちの悪い者)"
    )

    val jsonMock = "{\"japanese_mean\": \"悪い\",\"example\": [\"The food was so bad that I couldn't finish it.\",\"He had a bad feeling about the situation.\",\"She made a bad decision without thinking it through.\",\"It's a bad habit to procrastinate.\",\"That was a bad joke; no one laughed.\",\"I felt bad after forgetting her birthday.\",\"The weather is really bad today; it's raining heavily.\",\"He always has a bad attitude towards authority.\",\"It's bad luck when a black cat crosses your path.\",\"She has a bad reputation in the industry.\"],\"synonym\": [{\"word\": \"awful\",\"mean\": \"ひどい\"},{\"word\": \"terrible\",\"mean\": \"恐ろしい\"},{\"word\": \"poor\",\"mean\": \"貧弱な\"},{\"word\": \"horrible\",\"mean\": \"恐ろしい\"},{\"word\": \"dreadful\",\"mean\": \"恐ろしい\"},{\"word\": \"atrocious\",\"mean\": \"ひどい\"},{\"word\": \"unsatisfactory\",\"mean\": \"不満足な\"},{\"word\": \"subpar\",\"mean\": \"標準以下の\"},{\"word\": \"inferior\",\"mean\": \"劣っている\"},{\"word\": \"lousy\",\"mean\": \"劣悪な\"}],\"co_occurrences\": [\"bad news\",\"bad behavior\",\"bad mood\",\"bad attitude\",\"bad influence\",\"bad experience\",\"bad condition\",\"bad luck\",\"bad timing\",\"bad credit\"]}"
}