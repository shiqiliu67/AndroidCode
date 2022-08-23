package com.example.apidemo


import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface AccApiService {


@Headers(
    "Authorization: Basic bWtHaGMza3VOdnBzWEd6cThQT3o6MDQ3NGEwZjc0MTc1MTU2YzBmYzdkNDhmMWZlM2ViZmNiNjAwOTc5NzA2MDdhNjZhZmU5OTM0NmUxZmU0ZTE3Yg==",
    "Content-Type: application/json",
    "cache-control: no-cache"
)
@POST("/ci/search/get")
suspend fun getData(
    @Body body: String//RequestBody
) : Response<AccSearchResponse>

}