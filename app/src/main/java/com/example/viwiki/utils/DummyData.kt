package com.example.viwiki.utils

import com.example.viwiki.domain.page.Page
import com.example.viwiki.domain.page.PageResponse
import com.example.viwiki.domain.search.SearchResponse
import com.example.viwiki.domain.today.TodayResponse

val dummyPage = Page(
    1, "The title", "Lorem Ipsum"
)

val dummyPageResponse = PageResponse(
    PageResponse.Query(
        listOf(dummyPage)
    )
)

val dummyOnThisDay = TodayResponse.OnThisDay(
    text = "Ipsum nunc hendrerit eros nec massa ac aliquam sollicitudin nisl phasellus."
)
val dummyTodayResponse = TodayResponse(
    tfa = Page(),
    onThisDay = listOf(dummyOnThisDay)
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

val dummySearchResponse = SearchResponse(
    "",
    SearchResponse.SearchQuery(
        searchInfo = SearchResponse.SearchQuery.SearchInfo(0),
        search = listOf(
            SearchResponse.SearchQuery.Search(
                0, "", "Lorem ipsum", 0, ""
            )
        )
    )
)
