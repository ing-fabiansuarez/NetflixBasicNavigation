package me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import me.fabiansuarez.compose.lab.netflixbasicnavigation.data.sampleCategories

class HomeViewModel : ViewModel() {
    var categories by mutableStateOf(sampleCategories)
        private set
    
    var selectedTab by mutableStateOf(0)
        private set

    fun onTabSelected(index: Int) {
        selectedTab = index
    }
}
