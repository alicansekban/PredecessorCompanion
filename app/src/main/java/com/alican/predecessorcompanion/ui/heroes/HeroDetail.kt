package com.alican.predecessorcompanion.ui.heroes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alican.predecessorcompanion.custom.image.ImageView
import com.alican.predecessorcompanion.utils.HeroDetail

@Composable
fun HeroDetailScreen(
    modifier: Modifier = Modifier,
    heroDetail: HeroDetail
) {

    Column(modifier.fillMaxSize()) {
        Text(text = heroDetail.heroName)
        Text(text = heroDetail.heroId)
        Text(text = heroDetail.role)
        ImageView(imageUrl = heroDetail.image, modifier = Modifier.fillMaxWidth())
    }
}