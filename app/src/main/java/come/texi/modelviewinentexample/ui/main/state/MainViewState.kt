package come.texi.modelviewinentexample.ui.main.state

import come.texi.modelviewinentexample.model.BlogPost
import come.texi.modelviewinentexample.model.User

data class MainViewState(
    val blogPost: List<BlogPost>,
    val user: User?=null
)