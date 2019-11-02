package come.texi.modelviewinentexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import come.texi.modelviewinentexample.api.MyRetrofitBuilder
import come.texi.modelviewinentexample.ui.main.state.MainViewState
import come.texi.modelviewinentexample.utils.ApiEmptyResponse
import come.texi.modelviewinentexample.utils.ApiErrorResponse
import come.texi.modelviewinentexample.utils.ApiSuccessResponse

object Repository {

    fun getBlogPosts():LiveData<MainViewState>
    {
        return Transformations.switchMap(MyRetrofitBuilder.apiService.getBlogPost())
        {response->
            object : LiveData<MainViewState>()
            {
                override fun onActive() {
                    super.onActive()
                    when(response)
                    {
                        is ApiSuccessResponse->
                        {
                            value= MainViewState(blogPost = response.body)
                        }
                        is ApiErrorResponse->
                        {
                            value= MainViewState()
                        }
                        is ApiEmptyResponse->
                        {
                            value= MainViewState()
                        }
                    }
                }
            }
        }
    }


    fun getUser( userId:String):LiveData<MainViewState>
    {
        return  Transformations.switchMap(MyRetrofitBuilder.apiService.getUser(userId)){
            object : LiveData<MainViewState>(){
                override fun onActive() {
                    super.onActive()
                    when(it)
                    {
                        is ApiSuccessResponse->
                        {
                            value= MainViewState(user = it.body)
                        }
                        is ApiErrorResponse->
                        {
                            value= MainViewState()
                        }
                        is ApiEmptyResponse->
                        {
                            value=MainViewState()
                        }
                    }
                }
            }
        }
    }
}