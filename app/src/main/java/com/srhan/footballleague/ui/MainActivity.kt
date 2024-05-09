package com.srhan.footballleague.ui

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.srhan.footballleague.R
import com.srhan.footballleague.ui.common.preventScreenshotsAndRecords
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preventScreenshotsAndRecords()
        initSplashScreen()
        setContentView(R.layout.activity_main)

    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun initSplashScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            installSplashScreen()
        } else {
            setTheme(R.style.Theme_FootballLeague)
        }

    }
}