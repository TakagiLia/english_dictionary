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
}