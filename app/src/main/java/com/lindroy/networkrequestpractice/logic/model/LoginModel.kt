package com.lindroy.networkrequestpractice.logic.model

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */
data class LoginModel(
    val admin: Boolean?,
    val chapterTops: List<Any>?,
    val email: String?,
    val icon: String?,
    val id: Int?,
    val nickname: String?,
    val publicName: String?,
    val username: String?
)