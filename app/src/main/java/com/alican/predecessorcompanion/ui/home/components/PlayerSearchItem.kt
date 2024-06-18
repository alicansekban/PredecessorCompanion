
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alican.museums.utils.widthPercent
import com.alican.predecessorcompanion.custom.image.ImageView
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel

@Composable
fun PlayerSearchItem(player: PlayersUIModel, openPlayerDetail: (String) -> Unit) {
    val configuration = LocalConfiguration.current
    Card(
        modifier = Modifier
            .wrapContentSize()
            .widthPercent(0.45f, configuration)
            .padding(8.dp)
            .clickable {
                openPlayerDetail(player.id)
            },
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ImageView(imageUrl = player.rankIcon, modifier = Modifier.size(100.dp))
            Text(
                text = player.name,
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Global Ranking: ${player.rankActive}",
                fontSize = 10.sp,
            )
            Text(
                text = "MMR: ${player.mmr}",
                fontSize = 10.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}
