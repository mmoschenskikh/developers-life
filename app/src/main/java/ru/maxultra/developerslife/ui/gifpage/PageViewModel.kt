package ru.maxultra.developerslife.ui.gifpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.maxultra.developerslife.network.NetworkGif

enum class GifApiStatus { LOADING, DONE, ERROR }

abstract class PageViewModel : ViewModel() {
    protected val _status = MutableLiveData<GifApiStatus>()
    val status: LiveData<GifApiStatus>
        get() = _status

    protected val _gif = MutableLiveData<NetworkGif>()
    val gif: LiveData<NetworkGif>
        get() = _gif

    protected val previousGifs = mutableListOf<NetworkGif>()

    protected val position = MutableLiveData<Int>()

    val prevEnabled: LiveData<Boolean> = Transformations.map(position) { pos -> pos > 0 }
    val nextClickable: LiveData<Boolean> =
        Transformations.map(status) { status -> status != GifApiStatus.LOADING }

    protected abstract fun getGifPost()

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
