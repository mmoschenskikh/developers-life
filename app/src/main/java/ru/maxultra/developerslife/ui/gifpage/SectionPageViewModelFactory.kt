package ru.maxultra.developerslife.ui.gifpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maxultra.developerslife.network.GifSection

class SectionPageViewModelFactory(private val section: GifSection) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SectionPageViewModel::class.java)) {
            return SectionPageViewModel(section) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
