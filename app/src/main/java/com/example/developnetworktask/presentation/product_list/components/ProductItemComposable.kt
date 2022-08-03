package com.example.developnetworktask.presentation.product_list.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.developnetworktask.domain.model.ProductItem
import kotlin.math.ceil


@Composable
fun ProductItemComposable(productItem: ProductItem, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFE8EDFA),
        modifier = modifier.padding(10.dp)
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                verticalArrangement = Arrangement.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.wrapContentSize(),
                    color = Color(0xFFD1D5E1)
                ) {
                    Text(
                        text = productItem.brand,
                        style = TextStyle(fontSize = 12.sp),
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = productItem.title,
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                )
                Spacer(modifier = Modifier.height(2.dp))

                Text(text = "Price : ${productItem.price}L.E")
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = productItem.description,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "{${ceil(productItem.rating)}}",
                        style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    for (i in 1..ceil(productItem.rating).toInt()) {
                        Icon(
                            painter = painterResource(id = com.example.developnetworktask.R.drawable.ic_star),
                            tint = Color(0xFFF6B266),
                            contentDescription = null
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                OutlinedButton(
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black
                    ),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "See Description",

                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(16.dp),

                ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(productItem.thumbnail)
                        .crossfade(true)
                        .build(),
                    modifier = Modifier
                        .size(width = 200.dp, height = 140.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = "img"
                )
            }
        }
    }
}@Composable
fun ProductItemComposableStockOver(productItem: ProductItem, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFE8EDFA),
        modifier = modifier.padding(10.dp)
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                verticalArrangement = Arrangement.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.wrapContentSize(),
                    color = Color(0xFFD1D5E1)
                ) {
                    Text(
                        text = productItem.brand,
                        style = TextStyle(fontSize = 12.sp),
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(text = "Price : ${productItem.price}L.E")
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = productItem.title,
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                )
                Text(
                    text = productItem.description,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )



                Spacer(modifier = Modifier.height(2.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "{${ceil(productItem.rating)}}",
                        style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    for (i in 1..ceil(productItem.rating).toInt()) {
                        Icon(
                            painter = painterResource(id = com.example.developnetworktask.R.drawable.ic_star),
                            tint = Color(0xFFF6B266),
                            contentDescription = null
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                OutlinedButton(
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black
                    ),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "See Description",

                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(16.dp),

                ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(productItem.thumbnail)
                        .crossfade(true)
                        .build(),
                    modifier = Modifier
                        .size(width = 200.dp, height = 140.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = "img"
                )
            }
        }
    }
}