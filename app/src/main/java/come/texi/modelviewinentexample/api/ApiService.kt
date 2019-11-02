package come.texi.modelviewinentexample.api

import androidx.lifecycle.LiveData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import come.texi.modelviewinentexample.model.BlogPost
import come.texi.modelviewinentexample.model.User
import come.texi.modelviewinentexample.utils.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
        @GET("placeholder/user/{userId}")
        fun getUser(
            @Path("userId") userId:String
        ):LiveData<GenericApiResponse<User>>

    @GET("placeholder/blogs")
    fun getBlogPost():LiveData<GenericApiResponse<List<BlogPost>>>

}

