package com.alican.predecessorcompanion.custom.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alican.predecessorcompanion.data.remote.api.WebService
import com.alican.predecessorcompanion.data.remote.response.leaderBoard.LeaderBoardResponse
import retrofit2.HttpException
import java.io.IOException

class PlayerPagingSource(
    private val webService: WebService,
    private val name: String
) : PagingSource<Int, LeaderBoardResponse>() {

    override fun getRefreshKey(state: PagingState<Int, LeaderBoardResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LeaderBoardResponse> {
        val page = params.key ?: 1

        return try {
            val response = webService.getLeaderBoard(page, name)

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
