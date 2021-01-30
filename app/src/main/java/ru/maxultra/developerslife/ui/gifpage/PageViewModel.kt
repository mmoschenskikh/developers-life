package ru.maxultra.developerslife.ui.gifpage

import androidx.lifecycle.*
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

    private val previousGifs = mutableListOf<NetworkGif>()

    private val position = MutableLiveData<Int>()

    val prevEnabled: LiveData<Boolean> = Transformations.map(position) { pos -> pos > 0 }
    val nextClickable: LiveData<Boolean> =
        Transformations.map(status) { status -> status != GifApiStatus.LOADING }

    init {
        position.value = -1
        getGifPost()
    }

    private fun getGifPost() {
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

    fun onReload() {
        getGifPost()
    }

    fun onNext() {
        if (position.value!! + 1 == previousGifs.size) {
            getGifPost()
        } else {
            position.value = position.value!! + 1
            _gif.value = previousGifs[position.value!!]
        }
    }

    fun onPrev() {
        position.value = position.value?.minus(1) ?: 0
        _gif.value = previousGifs[position.value!!]
    }
}
