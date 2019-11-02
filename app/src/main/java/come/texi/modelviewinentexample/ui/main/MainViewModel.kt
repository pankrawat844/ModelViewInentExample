package come.texi.modelviewinentexample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import come.texi.modelviewinentexample.model.BlogPost
import come.texi.modelviewinentexample.model.User
import come.texi.modelviewinentexample.repository.Repository
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
                return Repository.getBlogPosts()

            }
            is MainStateEvent.GetUserEvent -> {
                return Repository.getUser(it.userId )
            }
            is MainStateEvent.None -> {
                return AbsentLiveData.create()

            }
        }
    }

    fun setBlogListData(blogPosts:List<BlogPost>) {
        val update = getCurrentViewStateOrNew()
        update.blogPost = blogPosts
        _viewState.value = update
    }

    fun setUser(user: User)
    {
        val update=getCurrentViewStateOrNew()
        update.user=user
        _viewState.value=update
    }

    fun getCurrentViewStateOrNew():MainViewState
    {
        val value=viewState.value?.let {
            it
        }?: MainViewState()
        return value
    }

    fun setStateEvent(event: MainStateEvent)
    {
        _stateEvent.value=event
    }
}