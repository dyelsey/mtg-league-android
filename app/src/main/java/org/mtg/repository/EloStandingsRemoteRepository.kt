package org.mtg.repository

import org.mtg.api.EloApi
import org.mtg.api.onApiErrorReturn
import org.mtg.flow.onError
import timber.log.Timber

class EloStandingsRemoteRepository(private val eloApi: EloApi) {
    fun standings() =
        eloApi.standings()
            .onError { Timber.w("elo standings could not be found") }
            .onApiErrorReturn { emptyList() }
}
