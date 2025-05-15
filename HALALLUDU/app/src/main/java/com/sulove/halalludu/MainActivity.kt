package com.sulove.halalludu

import android.os.Bundle

import androidx.compose.runtime.Composable
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.sulove.halalludu.ui.theme.HALALLUDUTheme


fun main(){
    val game= Ludogame()
    game.start()
}



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)

                }
            }


