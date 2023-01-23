package com.hobby_projects.myjcconverter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConverterType(
    modifier: Modifier = Modifier,
    iconDrawable: Int,
    measure: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable {
            onClick()
        },
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp,
        border = BorderStroke(1.dp, Color(204, 204, 204, 255))
    ) {
        Column(
            modifier = modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painterResource(iconDrawable),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
            )
            Text(
                fontSize = 16.sp,
//                color = MaterialTheme.colors.primary,
//                color = Color(80, 80, 80, 255),
                text = measure
            )
        }
    }
}
