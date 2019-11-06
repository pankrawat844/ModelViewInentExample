package come.texi.modelviewinentexample.ui

import come.texi.modelviewinentexample.utils.DataState

interface DataStateListner {
fun onDataStateChange(dataState:DataState<*>?)

}