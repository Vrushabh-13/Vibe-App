import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.presentation.components.AppIcon
import com.vrushabhgaikar.vibeplayer.presentation.components.AppText
import com.vrushabhgaikar.vibeplayer.presentation.components.HorizontalSpacer
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun AppTopBar(topBarTitle: String,
              modifier: Modifier = Modifier,
              showBackButton: Boolean = false,
              onBackClick: () -> Unit = {}) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showBackButton) {

            AppIcon(
                painter = painterResource(R.drawable.ic_left_arrow),
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onBackClick()
                    }
            )
            HorizontalSpacer(12.dp)
            AppText(
                text = topBarTitle,
                color = White,
                fontSize = 22.sp
            )
            HorizontalSpacer(100.dp)
        } else {

            AppText(
                text = topBarTitle,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                AppIcon(
                    painter = painterResource(id = R.drawable.ic_notification)
                )
                AppIcon(
                    painter = painterResource(id = R.drawable.ic_profile)
                )
            }
        }
    }
}
