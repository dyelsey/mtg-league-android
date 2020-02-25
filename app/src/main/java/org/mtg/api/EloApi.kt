package org.mtg.api

import kotlinx.coroutines.flow.Flow
import org.mtg.model.PlayerElo
import retrofit2.http.GET

interface EloApi {
    @GET("overall_standings")
    fun standings(): Flow<List<PlayerElo>>
}
