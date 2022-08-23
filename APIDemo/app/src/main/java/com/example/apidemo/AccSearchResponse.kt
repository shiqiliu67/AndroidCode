package com.example.apidemo

data class AccSearchResponse(
    val debug: Debug,
    val featured: Featured,
    val qa: Qa,
    val search: Search//used for search
)

data class Debug(
    val brains: List<String>,
    val query: String,
    val query_sentences: List<String>,
    val question: String
)

data class Featured(
    val all_results: AllResults,
    val content: Content,
    val media: Media
)

data class Qa(
    val content: List<Any>,
    val media: List<Any>,
    val people: List<Any>
)

data class Search(
    val all_results: AllResultsX,
    val content: ContentX,
    val media: MediaX,
    val people: People
)

data class AllResults(
    val results: List<Result>
)

data class Content(
    val results: List<ResultX>
)

data class Media(
    val results: List<ResultXX>
)

data class Result(
    val brain: String,
    val distance: Double,
    val episode_content: String,
    val growth_topic: List<String>,
    val id: String,
    val industry: List<String>,
    val is_premium: Int,
    val max_segment_score: Double,
    val published_date: String,
    val score: Double,
    val sentences: List<String>,
    val sentences_after: List<String>,
    val sentences_before: List<String>,
    val start_time: Int,
    val thumbnail_url: String,
    val title: String,
    val url: String
)

data class ResultX(
    val brain: String,
    val distance: Double,
    val episode_content: String,
    val growth_topic: List<String>,
    val id: String,
    val industry: List<String>,
    val is_premium: Int,
    val max_segment_score: Double,
    val published_date: String,
    val score: Double,
    val sentences: List<String>,
    val sentences_after: List<String>,
    val sentences_before: List<String>,
    val start_time: Int,
    val thumbnail_url: String,
    val title: String,
    val url: String
)

data class ResultXX(
    val brain: String,
    val distance: Double,
    val episode_content: String,
    val growth_topic: List<String>,
    val id: String,
    val industry: List<String>,
    val is_premium: Int,
    val max_segment_score: Double,
    val published_date: String,
    val score: Double,
    val sentences: List<String>,
    val sentences_after: List<String>,
    val sentences_before: List<String>,
    val start_time: Double,
    val thumbnail_url: String,
    val title: String,
    val url: String
)

data class AllResultsX(
    val results: List<ResultXXX>
)

data class ContentX(
    val results: List<ResultXXXX>
)

data class MediaX(
    val results: List<ResultXXXXX>
)

data class People(
    val results: List<ResultXXXXXX>
)

data class ResultXXX(
    val brain: String,
    val distance: Double,
    val episode_content: String,
    val growth_topic: List<String>,
    val id: String,
    val industry: List<String>,
    val is_premium: Int,
    val max_segment_score: Double,
    val published_date: String,
    val score: Double,
    val sentences: List<String>,
    val sentences_after: List<String>,
    val sentences_before: List<String>,
    val start_time: Double,
    val thumbnail_url: String,
    val title: String,
    val url: String
)

data class ResultXXXX(
    val brain: String,
    val distance: Double,
    val episode_content: String,
    val growth_topic: List<String>,
    val id: String,
    val industry: List<String>,
    val is_premium: Int,
    val max_segment_score: Double,
    val published_date: String,
    val score: Double,
    val sentences: List<String>,
    val sentences_after: List<String>,
    val sentences_before: List<String>,
    val start_time: Int,
    val thumbnail_url: String,
    val title: String,
    val url: String
)

data class ResultXXXXX(
    val brain: String,
    val distance: Double,
    val episode_content: String,
    val growth_topic: List<String>,
    val id: String,
    val industry: List<String>,
    val is_premium: Int,
    val max_segment_score: Double,
    val published_date: String,
    val score: Double,
    val sentences: List<String>,
    val sentences_after: List<String>,
    val sentences_before: List<String>,
    val start_time: Double,
    val thumbnail_url: String,
    val title: String,
    val url: String
)

data class ResultXXXXXX(
    val articles: List<Article>,
    val author_email: String,
    val author_image: String,
    val author_name: String,
    val author_score: Double,
    val author_summary: String,
    val author_title: String,
    val brain: String,
    val distance: Double,
    val id: String,
    val linkedin_url: String,
    val score: Double,
    val sentences: List<String>,
    val sentences_after: List<String>,
    val sentences_before: List<String>,
    val start_time: Int
)

data class Article(
    val thumbnail_url: String,
    val title: String,
    val url: String
)