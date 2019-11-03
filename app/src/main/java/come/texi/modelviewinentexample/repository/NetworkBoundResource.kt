package come.texi.modelviewinentexample.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import come.texi.modelviewinentexample.ui.main.state.MainViewState
import come.texi.modelviewinentexample.utils.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

abstract class NetworkBoundResource<ResponseObject,ViewStateType>
{
protected val result=MediatorLiveData<DataState<ViewStateType>>()

    init {
        result.value=DataState.loading(true)
        GlobalScope.launch(IO){
            delay(1000L)
            withContext(Main){
                val apiResponse=createCall()
                result.addSource(apiResponse)
                {response->
                    result.removeSource(apiResponse)
                    handleNetworkCall(response)
                }
            }
        }

    }

    fun handleNetworkCall(response: GenericApiResponse<ResponseObject>)
    {
        when(response)
        {
            is ApiSuccessResponse ->
            {
                handlAPiSuccessResponse(response)
            }
            is ApiErrorResponse ->
            {
                Log.e("Debug", "Networkboundresource ${response.errorMessage}")
                onReturnError(response.errorMessage)
            }
            is ApiEmptyResponse ->
            {
                Log.e("Debug", "Networkboundresource Noting Found")
                onReturnError("Noting Found")
            }
        }
    }

    fun onReturnError(message:String)
    {
        result.value= DataState.error(message)
    }
    abstract fun handlAPiSuccessResponse(response: ApiSuccessResponse<ResponseObject>)
    abstract fun createCall():LiveData<GenericApiResponse<ResponseObject>>

    fun asLiveData()=result as LiveData<DataState<ViewStateType>>
}

