
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.domain.model.MediaItemModel
import com.vrushabhgaikar.vibeplayer.domain.model.MediaType
import com.vrushabhgaikar.vibeplayer.domain.model.PlaceholderType
import com.vrushabhgaikar.vibeplayer.presentation.components.AppGradientOverlay
import com.vrushabhgaikar.vibeplayer.presentation.components.AppIcon
import com.vrushabhgaikar.vibeplayer.presentation.components.AppImage
import com.vrushabhgaikar.vibeplayer.presentation.components.AppText
import com.vrushabhgaikar.vibeplayer.presentation.components.HorizontalSpacer
import com.vrushabhgaikar.vibeplayer.presentation.components.VerticalSpacer
import com.vrushabhgaikar.vibeplayer.ui.theme.LightGray
import com.vrushabhgaikar.vibeplayer.ui.theme.PurplePrimary
import com.vrushabhgaikar.vibeplayer.ui.theme.White
import com.vrushabhgaikar.vibeplayer.utils.TimeUtils

@Composable
fun AppVideoListItem(media: MediaItemModel,
                     onClick: () -> Unit,
                     onIsFavClick: () -> Unit = {}){
    Column{
        Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 16.dp, vertical = 10.dp)
               .clickable {
                   onClick()
               },
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .border(
                        width = 0.1.dp,
                        color = LightGray,
                        shape = RoundedCornerShape(10)
                    )
            ){
                AppImage(
                    model = media.thumbnailUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 140.dp, height = 80.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    placeholderType = PlaceholderType.VIDEO
                )
                AppGradientOverlay(modifier = Modifier.matchParentSize())
                AppIcon(
                    painter = painterResource(id =
                        if(media.mediaType == MediaType.AUDIO)
                            R.drawable.img_music_icon
                        else
                            R.drawable.img_video_icon),
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(7.dp)
                        .size(28.dp)
                        .background(
                            Color.Black.copy(alpha = 0.5f),
                            CircleShape
                        )
                        .padding(6.dp)
                )

                AppText(
                    text = TimeUtils.formatDuration(media.duration),
                    color = White,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(6.dp)
                        .background(
                            Color.Black.copy(alpha = 0.7f),
                            RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            HorizontalSpacer(12.dp)

            Column(modifier = Modifier.weight(1f)) {

                AppText(media.title?:"", color = White, fontSize = 14.sp, maxLines = 2)

                VerticalSpacer(4.dp)

                AppText(media.artist?:"", color = LightGray, fontSize = 12.sp)

                VerticalSpacer(2.dp)

//                AppText(
//                    "8.4M views • 1 month ago",
//                    color = LightGray,
//                    fontSize = 11.sp
//                )
            }
            Column(horizontalAlignment = Alignment.End) {

                AppIcon(
                    painter = painterResource(if(media.isFav)
                        R.drawable.ic_heart_fill
                    else
                        R.drawable.ic_like),
                    contentDescription = null,
                    tint = if(media.isFav)
                        PurplePrimary
                    else
                        LightGray,
                    modifier = Modifier.size(22.dp)
                        .clip(shape = CircleShape)
                        .clickable{
                            onIsFavClick()
                        }
                )

//                VerticalSpacer(10.dp)
//
//                AppIcon(painter = painterResource(id = R.drawable.ic_like), null, tint = White)
            }
        }
        HorizontalDivider(Modifier, thickness = 0.5.dp, color = LightGray.copy(alpha = 0.1f))
    }
}