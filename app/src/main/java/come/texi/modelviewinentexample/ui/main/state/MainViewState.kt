package come.texi.modelviewinentexample.ui.main.state

import come.texi.modelviewinentexample.model.BlogPost
import come.texi.modelviewinentexample.model.User

data class MainViewState(
    var blogPost: List<BlogPost>?=null,
    var user: User?=null
)