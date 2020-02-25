package org.mtg.screen.elo

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mtg.di.Modules

class EloModules : Modules {
    override val modules: List<Module> = listOf(standingsScreen())

    private fun standingsScreen() = module {
        viewModel { EloViewModel(get()) }
    }
}
