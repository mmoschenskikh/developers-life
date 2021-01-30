package ru.maxultra.developerslife.ui.gifpage

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.maxultra.developerslife.network.GifApi
import ru.maxultra.developerslife.network.GifSection

class SectionPageViewModel(private val section: GifSection) : PageViewModel() {
    private var page = 0

    init {
        position.value = -1
        getGifPost()
    }

    override fun getGifPost() {
        viewModelScope.launch {
            _status.value = GifApiStatus.LOADING
            try {
                val response = GifApi.retrofitService.getSectionImages(section.alias, page++)
                previousGifs.addAll(response.gifs)
                position.value = position.value!! + 1
                _gif.value = previousGifs[position.value!!]
                _status.value = GifApiStatus.DONE
            } catch (e: Exception) {
                _status.value = GifApiStatus.ERROR
            }
        }
    }
}
