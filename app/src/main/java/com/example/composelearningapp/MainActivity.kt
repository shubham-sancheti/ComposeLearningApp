package com.example.composelearningapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composelearningapp.screens.getDescriptionList
import com.example.composelearningapp.screens.getHomeScreen
import com.example.composelearningapp.ui.theme.ComposeLearningAppTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      /*  val imWaitingten = lifecycleScope.async(Dispatchers.Main) {
            var i = 0
            while(i<10){
                Log.e("waitingTest", "FIRST "+ i )
                delay(1000)
                i++
            }
        }
        val imWaitingtwenty = lifecycleScope.async(Dispatchers.Main) {
            var i = 0
            while(i<10){
                Log.e("waitingTest", "SECOND "+ i )
                delay(2000)
                i++
            }
        }*/
       /* runBlocking (Dispatchers.IO){
            var i = 0
            while(i<10){
                Log.e("waitingTest", "Third "+ i )
                delay(3000)
                i++
            }

        }*/
       /* val item = lifecycleScope.async(Dispatchers.Main) {
            val asyncs = listOf(imWaitingten, imWaitingtwenty).awaitAll()
            Log.e("waitingCompleted",Gson().toJson(asyncs) )
        }*/

        setContent {
            ComposeLearningAppTheme {
                val snackbar = remember{SnackbarHostState()}
                // A surface container using the 'background' color from the theme
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "Compose Learning")
                        })
                    }, snackbarHost = { SnackbarHost(snackbar) }
                ) {
                    //Scaffold wont work until u use its padding
                    Box(Modifier.padding(it)) {
                        App(snackbar)
                    }
                }
            }
        }
    }
}

fun onCategorySelected(item:String){
    Log.e("onItemClicked", item)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeLearningAppTheme {
        Greeting("Android")
    }
}

@Composable
fun App(snackbar: SnackbarHostState) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = "category"){
        composable(route = "category"){
            getHomeScreen{
                scope.launch {
                    snackbar.showSnackbar("$it selected.")
                }
                navController.navigate("detail/${it}")
            }
        }
        composable(route = "detail/{category}",
            arguments = listOf(
                navArgument("category"){
                    type = NavType.StringType
                }
            )
        ){
            getDescriptionList()
        }
    }
}