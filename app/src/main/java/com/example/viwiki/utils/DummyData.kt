package com.example.viwiki.utils

import com.example.viwiki.model.ArticleResponse
import com.example.viwiki.model.FeaturedArticleResponse

val dummyArticle = ArticleResponse.Query.Page(
    1, 1, "Lorem Ipsum",
    "Massa proin ipsum adipiscing nisi eros placerat adipiscing interdum massa rutrum a pellentesque tempus accumsan nisi mi dolor erat elit magna placerat arcu proin felis." +
            "Aliquam tortor suspendisse eget elementum tortor interdum erat congue sollicitudin eu molestie elit tortor ipsum nunc hendrerit eros nec massa ac aliquam sollicitudin nisl phasellus."
)

val dummyArticleResponse = ArticleResponse(
    true,
    ArticleResponse.Query(
        listOf(dummyArticle)
    )
)

val dummyOnThisDayArticle = FeaturedArticleResponse.OnThisDayArticle(
    text = "Ipsum nunc hendrerit eros nec massa ac aliquam sollicitudin nisl phasellus."
)
val dummyFeaturedArticleResponse = FeaturedArticleResponse(
    tfa = FeaturedArticleResponse.Tfa("type", "Title", "Title"),
    onThisDay = listOf(dummyOnThisDayArticle)
)
