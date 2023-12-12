package com.example.composelearningapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composelearningapp.di.ApiClient
import com.example.composelearningapp.models.TweetListItem
import com.example.composelearningapp.viewmodel.DesciptionViewModel


@Composable
fun getDescriptionItem(item: TweetListItem){
    Card(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(2.dp))
            .padding(12.dp), border = BorderStroke(1.dp, Color.Gray)
    , content = {
        Text(text =  item.text,Modifier.padding(12.dp), color = Color.Black, style = MaterialTheme.typography.bodyMedium)
        })

}

val descriptionList = arrayListOf("a","b")
@Preview
@Composable
fun getDescriptionList(){
     val viewModel: DesciptionViewModel =  hiltViewModel()
    val response = viewModel.listState.value
    when(response){
        is ApiClient.ApiResponse.ERROR -> {

        }
        is ApiClient.ApiResponse.SUCCESS -> {
            LazyColumn(content ={
                items(response.reponse){
                    getDescriptionItem(it)
                }
            })
        }
        ApiClient.ApiResponse.loading -> {

        }
    }

}