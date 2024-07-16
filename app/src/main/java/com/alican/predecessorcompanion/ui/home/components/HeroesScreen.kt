package com.alican.predecessorcompanion.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alican.museums.utils.noRippleClick
import com.alican.predecessorcompanion.domain.ui_model.heroes.HeroesStatisticsUIModel

@Composable
fun HeroesScreen(
    hero: HeroesStatisticsUIModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .noRippleClick {
                onClick.invoke()
            }
            .padding(8.dp)
            .width(100.dp)
            .height(130.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            hero.heroName?.let {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = it,
                    maxLines = 1,
                )
            }
            hero.pickRate?.let {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = it,
                    maxLines = 1,
                )
            }

        }
    }
}


