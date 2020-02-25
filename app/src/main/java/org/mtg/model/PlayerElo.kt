package org.mtg.model

data class PlayerElo(
    val userId: Long,
    val firstName: String = "",
    val lastName: String = "",
    val overallLosses: Long = 0,
    val overallWins: Long = 0,
    val overallPoints: Long = 0,
    val overallRecord: String = "",
    val totalMatches: Long = 0,
    val winPercentage: Long = 0,
    val individualLeagueRecords: List<LeagueRecord> = emptyList(),
    val pro: Boolean = false,
    val elo: Long = 0
)
