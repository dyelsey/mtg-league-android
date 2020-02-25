package org.mtg.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.mtg.screen.elo.EloModules
import org.mtg.screen.leagues.LeagueModules
import org.mtg.screen.play.PlayModules
import org.mtg.screen.report.ReportModules
import org.mtg.screen.settings.SettingsModules
import org.mtg.screen.standings.StandingsModules

object ApplicationInjector {
    fun inject(context: Context) {
        startKoin {
            androidContext(context)
            modules(modules())
        }
    }

    private fun modules(): List<Module> =
        listOf(
            CommonModules(),
            EloModules(),
            LeagueModules(),
            PlayModules(),
            ReportModules(),
            SettingsModules(),
            StandingsModules()
        ).flatMap { it.modules }
}
