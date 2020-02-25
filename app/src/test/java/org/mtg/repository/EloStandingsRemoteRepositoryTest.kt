package org.mtg.repository

import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mtg.api.EloApi
import org.mtg.flow.sendSingle
import org.mtg.flow.testChannel
import org.mtg.flow.testCollect
import org.mtg.model.PlayerElo
import java.io.IOException

@RunWith(JUnit4::class)
class EloStandingsRemoteRepositoryTest {
    private companion object {
        val ERROR = IOException()
        val PLAYER = PlayerElo(
            userId = 1,
            firstName = "John",
            lastName = "Smith"
        )
    }

    private val api = mock<EloApi>()
    private val standingChannel = api.standings().testChannel()

    private val repo = EloStandingsRemoteRepository(api)

    @Test
    fun standing_error() = runBlockingTest {
        val collector = repo.standings().testCollect(this)
        respondWithStandingError()
        collector.assertValues(emptyList()).assertNoError()
    }

    @Test
    fun standing_success() = runBlockingTest {
        val collector = repo.standings().testCollect(this)
        respondWithStandings()
        collector.assertValues(listOf(PLAYER)).assertNoError()
    }

    private suspend fun respondWithStandings() = standingChannel.sendSingle(listOf(PLAYER))

    private fun respondWithStandingError() = standingChannel.close(ERROR)
}
