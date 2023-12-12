package com.example.composelearningapp.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composelearningapp.R
import com.example.composelearningapp.di.ApiClient
import com.example.composelearningapp.viewmodel.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun getCategoryItem(item:String, click:(item:String)->Unit){
    Card(
       modifier = Modifier
           .padding(8.dp)
           .clip(RoundedCornerShape(8.dp))
           .height(120.dp).clickable {
               click(item)
           }, border = BorderStroke(1.dp,Color.Gray), elevation = CardDefaults.cardElevation(5.dp)){
      Box (contentAlignment = Alignment.BottomCenter){
          Image(painter = painterResource(id = R.drawable.bg_item), contentDescription = "test", modifier = Modifier.fillMaxSize(1f), contentScale = ContentScale.Crop)
          Text(text = item, fontSize = TextUnit(12f, TextUnitType.Sp), color = Color.Black, modifier = Modifier.padding(8.dp), textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineSmall)
      }

    }
}
@Composable
fun getHomeScreen( click:(item:String)->Unit){
    val context = LocalContext.current
    val viewmodel:HomeViewModel = hiltViewModel()
    Box (Modifier.padding(8.dp)){
        LaunchedEffect(key1 = true, block = {
            viewmodel.loadCategories()
        })
        val response = viewmodel.state.value
        when(response){
            is ApiClient.ApiResponse.ERROR -> {
                Toast.makeText(context,"Something went wrong.", Toast.LENGTH_SHORT).show()
            }
            is ApiClient.ApiResponse.SUCCESS -> {
                LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
                    items(response.reponse.distinct()){
                        getCategoryItem(it,click)
                    }
                })
            }
            ApiClient.ApiResponse.loading -> {
                Text(text = "Loading", modifier =  Modifier.fillMaxSize(1f), textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineMedium)
            }
        }

    }
}

@Composable
fun loadGrid(){

}
