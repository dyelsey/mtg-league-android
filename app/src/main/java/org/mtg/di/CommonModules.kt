package org.mtg.di

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope
import org.koin.dsl.bind
import org.koin.dsl.module
import org.mtg.Database
import org.mtg.api.*
import org.mtg.domain.CurrentLeagueUseCase
import org.mtg.repository.*
import org.mtg.util.BottomNavigationHelper
import org.rx.MagicSchedulers
import org.rx.ProductionSchedulers

class CommonModules : Modules {

    override val modules = listOf(
        api(),
        database(),
        helpers(),
        repos(),
        schedulers(),
        useCases()
    )

    private fun api() = module {
        single { ApiFactory(get()) }
        single { createApi<EloApi>(get()) }
        single { createApi<LeagueApi>(get()) }
        single { createApi<MatchResultApi>(get()) }
        single { createApi<StandingApi>(get()) }
        single { createApi<UserApi>(get()) }
    }

    private fun database() = module {
        single { AndroidSqliteDriver(Database.Schema, get(), "mtg.db") } bind SqlDriver::class
        single { Database(get()) }
        single { db().settingsQueries }
    }

    private fun helpers() = module {
        factory { BottomNavigationHelper() }
    }

    private fun repos() = module {
        factory { EloStandingsRemoteRepository(get()) }
        factory { LeagueRemoteRepository(get()) }
        factory { MatchRemoteRepository(get()) }
        factory { StandingRemoteRepository(get()) }
        factory { SettingsLocalRepository(get(), get()) }
        factory { UserRemoteRepository(get()) }
    }

    private fun useCases() = module {
        single { CurrentLeagueUseCase(get(), get()) }
    }

    private fun schedulers() = module {
        factory { ProductionSchedulers() } bind MagicSchedulers::class
    }

    private inline fun <reified T> createApi(apiFactory: ApiFactory) = apiFactory.createApi<T>()

    private fun Scope.db(): Database = get()

}
