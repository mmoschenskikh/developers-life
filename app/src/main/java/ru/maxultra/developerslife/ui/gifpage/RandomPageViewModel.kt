package ru.maxultra.developerslife.ui.gifpage

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.maxultra.developerslife.network.GifApi

class RandomPageViewModel : PageViewModel() {
    init {
        position.value = -1
        getGifPost()
    }

    override fun getGifPost() {
        viewModelScope.launch {
            _status.value = GifApiStatus.LOADING
            try {
                _gif.value = GifApi.retrofitService.getRandomImage()
                gif.value?.let { previousGifs.add(it) }
                position.value = position.value!! + 1
                _status.value = GifApiStatus.DONE
            } catch (e: Exception) {
                _status.value = GifApiStatus.ERROR
            }
        }
    }
}
