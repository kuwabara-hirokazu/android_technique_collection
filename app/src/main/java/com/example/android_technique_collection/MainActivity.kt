package com.example.android_technique_collection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_technique_collection.feature.searchphoto.SearchPhotoScreen
import com.example.android_technique_collection.ui.theme.Android_technique_collectionTheme
import com.example.android_technique_collection.ui.theme.ScreenRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_technique_collectionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = rememberNavController(),
                        startDestination = ScreenRoute.HomeScreen.route
                    ) {
                        composable(ScreenRoute.HomeScreen.route) {
                            SearchPhotoScreen()
                        }
                    }
                }
            }
        }
    }
}
