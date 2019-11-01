package come.texi.modelviewinentexample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import come.texi.modelviewinentexample.ui.main.state.MainStateEvent
import come.texi.modelviewinentexample.ui.main.state.MainViewState
import come.texi.modelviewinentexample.utils.AbsentLiveData

class MainViewModel : ViewModel() {
    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState


    val dataState: LiveData<MainViewState> = Transformations
        .switchMap(_stateEvent){stateEvent ->
            stateEvent?.let {
                handleStateEvent(stateEvent)
            }
        }

    private fun handleStateEvent(it: MainStateEvent): LiveData<MainViewState> {
        when (it) {
            is MainStateEvent.GetBlogPostEvent -> {
                return AbsentLiveData.create()
            }
            is MainStateEvent.GetUserEvent -> {
                return AbsentLiveData.create()
            }
            is MainStateEvent.None -> {
                return AbsentLiveData.create()

            }
        }
    }
}