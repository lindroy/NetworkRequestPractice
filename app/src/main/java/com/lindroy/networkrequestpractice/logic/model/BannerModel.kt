package com.lindroy.networkrequestpractice.logic.model

/**
 * @author Lin
 * @date 2021/10/14
 * @function
 */
data class BannerModel(
    val desc: String? = "",
    val id: Int? = 0,
    val imagePath: String? = "",
    val isVisible: Int? = 0,
    val order: Int? = 0,
    val title: String? = "",
    val type: Int? = 0,
    val url: String? = ""
)