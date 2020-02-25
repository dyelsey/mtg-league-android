package org.mtg.screen.elo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.compose.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.ui.core.setContent
import org.koin.android.viewmodel.ext.android.viewModel
import org.mtg.R

class EloFragment : Fragment() {
    private val viewModel: EloViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_elo, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.standings().observe(viewLifecycleOwner, Observer { viewState ->
            if (viewState is EloViewModel.EloStandingsViewState.Success) {
                (view as ViewGroup).setContent {
                    EloScreen(viewState.players)
                }
            }
        })
    }
}
