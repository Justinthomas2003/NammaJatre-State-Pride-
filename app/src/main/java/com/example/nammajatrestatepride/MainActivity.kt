package com.example.nammajatrestatepride

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.nammajatrestatepride.navigation.AppNavigation
import com.example.nammajatrestatepride.ui.theme.NammaJatreStatePrideTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NammaJatreStatePrideTheme {
                AppNavigation()
            }
        }
    }
}