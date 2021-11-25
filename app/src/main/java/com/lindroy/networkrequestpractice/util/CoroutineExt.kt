package com.lindroy.networkrequestpractice.util

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @author Lin
 * @date 2021/11/19
 * @function
 */

fun ViewModel.launchFlow(block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch { block() }
}

fun AppCompatActivity.launchLifecycleScope(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch { block() }
}

