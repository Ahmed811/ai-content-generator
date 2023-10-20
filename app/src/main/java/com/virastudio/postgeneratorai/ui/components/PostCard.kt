package com.virastudio.postgeneratorai.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.virastudio.postgeneratorai.models.PostCardModel
import com.virastudio.postgeneratorai.models.postCardList

@Composable
fun PostCard(postCardModel:PostCardModel,onCardClick:()->Unit={}){
Card(modifier = Modifier
   .padding(10.dp).height(240.dp).clickable { onCardClick.invoke() }
    , elevation =CardDefaults.cardElevation(defaultElevation = 4.dp),  ) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(20.dp,10.dp),Arrangement.SpaceBetween,Alignment.CenterHorizontally) {
        Image(painterResource(id = postCardModel.image), contentDescription ="post generator",Modifier.size(80.dp) )
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = postCardModel.title, style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp), textAlign = TextAlign.Center)
    }

}

}








@Preview(showBackground = true)
@Composable
fun PostCardPreview(){
PostCard(postCardList.first())
}