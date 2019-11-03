package come.texi.modelviewinentexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import come.texi.modelviewinentexample.api.MyRetrofitBuilder
import come.texi.modelviewinentexample.model.BlogPost
import come.texi.modelviewinentexample.model.User
import come.texi.modelviewinentexample.ui.main.state.MainViewState
import come.texi.modelviewinentexample.utils.*

object Repository {

    fun getBlogPosts():LiveData<DataState<MainViewState>>
    {

                    return object:NetworkBoundResource<List<BlogPost>,MainViewState>()
                    {
                        override fun handlAPiSuccessResponse(response: ApiSuccessResponse<List<BlogPost>>) {
                            result.value= DataState.data(
                                data =
                                MainViewState(
                                    blogPost = response.body
                                )
                            )
                        }

                        override fun createCall(): LiveData<GenericApiResponse<List<BlogPost>>> {
                            return MyRetrofitBuilder.apiService.getBlogPost()
                        }

                    }.asLiveData()
    }


    fun getUser( userId:String):LiveData<DataState<MainViewState>>
    {
        return object :NetworkBoundResource<User,MainViewState>()
        {
            override fun handlAPiSuccessResponse(response: ApiSuccessResponse<User>) {
                result.value= DataState.data(
                    data = MainViewState(
                        user = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<User>> {
                return MyRetrofitBuilder.apiService.getUser(userId)
            }

        }.asLiveData()
    }
}