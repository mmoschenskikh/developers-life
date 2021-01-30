package ru.maxultra.developerslife.ui.gifpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.maxultra.developerslife.network.GifApi
import ru.maxultra.developerslife.network.NetworkGif

enum class GifApiStatus { LOADING, DONE, ERROR }

class PageViewModel : ViewModel() {

    private val _status = MutableLiveData<GifApiStatus>()
    val status: LiveData<GifApiStatus>
        get() = _status

    private val _gif = MutableLiveData<NetworkGif>()
    val gif: LiveData<NetworkGif>
        get() = _gif

    init {
        getGifPost()
    }

    private fun getGifPost() {
        viewModelScope.launch {
            _status.value = GifApiStatus.LOADING
            try {
                _gif.value = GifApi.retrofitService.getRandomImage()
                _status.value = GifApiStatus.DONE
            } catch (e: Exception) {
                _status.value = GifApiStatus.ERROR
            }
        }
    }
}
