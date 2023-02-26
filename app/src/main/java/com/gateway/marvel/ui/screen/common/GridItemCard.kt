package com.gateway.marvel.ui.screen.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp



@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun GridItemCard(
    image: Int,
    title: String,
    onItemClicked: () -> Unit
) {


    Card(
        onClick = onItemClicked,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.size(150.dp),
        elevation = 10.dp
    ) {
        Column {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxWidth()
                    .height(100.dp)
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null
                )
            }

            Text(text = title, textAlign = TextAlign.Start, modifier = Modifier.padding(4.dp))
        }
    }

}
