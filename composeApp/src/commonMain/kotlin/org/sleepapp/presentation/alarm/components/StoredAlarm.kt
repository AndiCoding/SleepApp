import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import org.sleepapp.data.model.Alarm

@Composable
fun StoredAlarm(
    alarm: Alarm,
    onActivate: (Alarm) -> Unit,
    onDelete: (Alarm) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    val animatedHeight by animateFloatAsState(
        targetValue = if (isExpanded) 0.3f else 0.15f,
        label = "height animation"
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable { isExpanded = !isExpanded }
            .animateContentSize()
    ) {
        Column {
            // Always visible content
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(alarm.id.toString())
                Text(alarm.endAlarm.toString())
            }

            // Expandable content
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        colors = buttonColors(containerColor = Color.LightGray),
                        onClick = { onActivate(alarm) }
                    ) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Default.Close),
                            contentDescription = "activate alarm"
                        )
                    }
                    Button(
                        colors = buttonColors(containerColor = Color.Red),
                        onClick = { onDelete(alarm) }
                    ) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Default.Close),
                            contentDescription = "Delete alarm"
                        )
                    }
                }
            }
        }
    }
}