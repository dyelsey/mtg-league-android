package org.mtg.screen.elo

import kotlinx.coroutines.rx2.asObservable
import org.mtg.model.PlayerElo
import org.mtg.repository.EloStandingsRemoteRepository
import org.mtg.viewmodel.MagicViewModel

class EloViewModel(
    private val eloStandingsRemoteRepository: EloStandingsRemoteRepository
) : MagicViewModel() {
    sealed class EloStandingsViewState {
        object InProgress : EloStandingsViewState()
        data class Success(val players: List<PlayerElo>) : EloStandingsViewState()
    }

    fun standings() =
        eloStandingsRemoteRepository.standings()
            .asObservable()
            .map<EloStandingsViewState> { players -> EloStandingsViewState.Success(players) }
            .startWith(EloStandingsViewState.InProgress)
            .toLiveData()
}
