package com.vk.usersapp.feature.feed

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow

object PagingDataHelper {
    private fun <T : Any> PagingData<T>.collectData(): List<T> {
        val data = mutableListOf<T>()
        this.map { item ->
            data.add(item)
        }
        return data
    }

    fun <T : Any> arePagingDataEqual(
        pagingData1: PagingData<T>,
        pagingData2: PagingData<T>
    ): Boolean {
        val list1 = pagingData1.collectData()
        val list2 = pagingData2.collectData()

        return list1 == list2
    }
}