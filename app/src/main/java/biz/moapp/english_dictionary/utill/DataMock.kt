package biz.moapp.english_dictionary.utill

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

    val jsonMock = "{\"japanese_mean\":[\"助言\",\"アドバイス\"],\"example\":[\"She gave me some good advice on how to improve my writing.\",\"It's always a good idea to seek advice from experts before making decisions.\",\"He followed her advice and it worked out perfectly.\",\"I appreciate your advice and will definitely consider it.\",\"When in doubt, consult your friends for advice.\",\"The advice you receive can greatly influence your choices.\",\"It's wise to heed the advice of those who have experience.\",\"Her advice was instrumental in my success.\",\"I often give advice to my peers based on my experiences.\",\"His advice helped me to see things from a different perspective.\"],\"synonym\":[{\"word\":\"suggestion\",\"mean\":\"提案\"},{\"word\":\"counsel\",\"mean\":\"助言\"},{\"word\":\"guidance\",\"mean\":\"指導\"},{\"word\":\"recommendation\",\"mean\":\"推奨\"}],\"antonyms\":[{\"word\":\"disregard\",\"mean\":\"無視\"},{\"word\":\"indifference\",\"mean\":\"無関心\"},{\"word\":\"neglect\",\"mean\":\"放置\"},{\"word\":\"oblivion\",\"mean\":\"忘却\"}],\"word_roots\":\"この単語は古いフランス語の「aviser」が由来で、これは「知らせる」や「考える」という意味です。\"}"
}