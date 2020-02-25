package org.mtg.screen.elo

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.DpConstraints
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.surface.Card
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import org.mtg.model.PlayerElo

@Composable
fun EloScreen(players: List<PlayerElo>) {
    VerticalScroller {
        Column {
            players.forEach { PlayerItem(it) }
        }
    }
}

@Composable
private fun PlayerItem(player: PlayerElo) {
    Container(alignment = Alignment.Center, width = 800.dp, expanded = true) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = LayoutPadding(left = 8.dp, right = 8.dp, top = 4.dp, bottom = 4.dp)
        ) {
            Container(modifier = LayoutPadding(16.dp)) {
                Column {
                    Text("name: ${player.firstName} ${player.lastName}")
                    Text("Overall wins: ${player.overallWins}")
                    Text("Overall losses: ${player.overallLosses}")
                    Text("Overall points: ${player.overallPoints}")
                    Text("Overall record: ${player.overallRecord}")
                    Text("Total matches: ${player.totalMatches}")
                    Text("Win percentage: ${player.winPercentage}")
                    Text("Pro: ${player.pro}")
                    Text("Elo: ${player.elo}")
                }
            }
        }
    }
}

fun tightConstraintsForHeight(height: Dp) = DpConstraints(
    minWidth = 0.dp,
    maxWidth = Dp.Infinity,
    minHeight = height,
    maxHeight = height
)

/*
val image = +imageResource(R.drawable.placeholder_1_1)
    Row(
        modifier = Spacing(left = 16.dp, right = 16.dp)
    ) {
        Container(modifier = Gravity.Center wraps Size(56.dp, 56.dp)) {
            Clip(RoundedCornerShape(4.dp)) {
                DrawImage(image)
            }
        }
        Text(
            text = itemTitle,
            modifier = Flexible(1f) wraps Gravity.Center wraps Spacing(16.dp),
            style = (+MaterialTheme.typography()).subtitle1)
        val selected = isTopicSelected(topicKey)
        SelectTopicButton(
            modifier = Gravity.Center,
            onSelected = {
                selectTopic(topicKey, !selected)
            },
            selected = selected
        )
    }
 */
