package com.example.mvi.viewmodel

import androidx.lifecycle.*
import com.example.mvi.model.Blog
import com.example.mvi.repository.MainRepo
import com.example.mvi.utils.DataState
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject  constructor(
    private val mainRepo: MainRepo,
     private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState = MutableLiveData<DataState<List<Blog>>>()

    val dataState: LiveData<DataState<List<Blog>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetBlogsEvent -> {
                    mainRepo.getBlogs().onEach { dataState ->
                        _dataState.value = dataState
                    }
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.NoneEvent -> {
                    //who cares
                }
            }

        }
    }

}


sealed class MainStateEvent {
    object GetBlogsEvent : MainStateEvent()
    object NoneEvent : MainStateEvent()
}