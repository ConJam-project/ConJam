package com.km.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.km.feature.home.RouteHome
import com.km.feature.main.MainNavigator
import com.km.feature.main.MainScreen
import com.km.feature.main.rememberMainNavigator
import com.km.feature.ui.theme.ConJamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigator: MainNavigator = rememberMainNavigator()

            ConJamTheme {
                MainScreen(
                    navigator = navigator,
                    onTabSelected = { tab -> navigator.navigate(tab) }
                )
            }
        }
    }
}