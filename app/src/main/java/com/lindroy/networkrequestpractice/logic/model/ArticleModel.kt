package com.lindroy.networkrequestpractice.logic.model

/**
 * @author Lin
 * @date 2021/10/18
 * @function
 */
data class HomeArticleModel(
    val curPage: Int? = 0,
    val datas: List<ArticleModel>? = listOf(),
    val offset: Int? = 0,
    val over: Boolean? = false,
    val pageCount: Int? = 0,
    val size: Int? = 0,
    val total: Int? = 0
)

data class ArticleModel(
    val apkLink: String? = "",
    val audit: Int? = 0,
    val author: String? = "",
    val canEdit: Boolean? = false,
    val chapterId: Int? = 0,
    val chapterName: String? = "",
    val collect: Boolean? = false,
    val courseId: Int? = 0,
    val desc: String? = "",
    val descMd: String? = "",
    val envelopePic: String? = "",
    val fresh: Boolean? = false,
    val host: String? = "",
    val id: Int? = 0,
    val link: String? = "",
    val niceDate: String? = "",
    val niceShareDate: String? = "",
    val origin: String? = "",
    val prefix: String? = "",
    val projectLink: String? = "",
    val publishTime: Long? = 0,
    val realSuperChapterId: Int? = 0,
    val selfVisible: Int? = 0,
    val shareDate: Long? = 0,
    val shareUser: String? = "",
    val superChapterId: Int? = 0,
    val superChapterName: String? = "",
    val tags: List<Tag>? = listOf(),
    val title: String? = "",
    val type: Int? = 0,
    val userId: Int? = 0,
    val visible: Int? = 0,
    val zan: Int? = 0
)

data class Tag(
    val name: String? = "",
    val url: String? = ""
)