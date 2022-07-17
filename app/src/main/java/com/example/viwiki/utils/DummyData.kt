package com.example.viwiki.utils

import com.example.viwiki.model.ArticleResponse

val dummyArticle = ArticleResponse.Query.Page(
    1, 1, "La gamba Berebere",
    "La gamba Berebere es la única gamba negra del mundo mundial"
)

val dummyArticleResponse = ArticleResponse(
    true,
    ArticleResponse.Query(
        listOf(dummyArticle)
    )
)
