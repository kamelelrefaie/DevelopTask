package com.example.developnetworktask.presentation.product_list.components


import CustomDialog
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun DialogProduct(productItem: ProductItem, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFE8EDFA),
        modifier = Modifier.background(Color.Black).padding(2.dp)
                .fillMaxSize()
    ) {

            Column(
                verticalArrangement = Arrangement.Center
            , modifier = Modifier.padding(10.dp)
            ) {

                Surface(
                    shape = RoundedCornerShape(16.dp),
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(productItem.thumbnail)
                            .crossfade(true)
                            .build(),
                        modifier = Modifier
                            .height(250.dp).fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                        contentDescription = "img"
                    )
                }


                Spacer(modifier = Modifier.height(4.dp))
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
                Text(
                    text = productItem.description,
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(text = "Price : ${productItem.price}L.E")

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
                        text = "Cancel",

                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                    )
                }
            }



    }
}