package com.example.viwiki.utils

import com.example.viwiki.article_detail.ArticleResponse
import com.example.viwiki.home.FeaturedArticleResponse
import com.example.viwiki.search.SearchResponse

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

val dummySearchQuery = SearchResponse.SearchQuery(
    search = listOf(
        SearchResponse.SearchQuery.Search(
            title = "Morbius"
        ),
        SearchResponse.SearchQuery.Search(
            title = "Avengers: Endgame"
        ),
        SearchResponse.SearchQuery.Search(
            title = "Avatar"
        )
    )
)

val dummySearchResponse = SearchResponse("",
    SearchResponse.SearchQuery(
        searchInfo = SearchResponse.SearchQuery.SearchInfo(0),
        search = listOf(
            SearchResponse.SearchQuery.Search(
                0, "", 0, 0, 0, "", ""
            )
        )
    )
)
