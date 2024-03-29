package come.texi.modelviewinentexample.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import come.texi.modelviewinentexample.R
import come.texi.modelviewinentexample.ui.DataStateListner
import come.texi.modelviewinentexample.ui.main.state.MainStateEvent
import come.texi.modelviewinentexample.ui.main.state.MainViewState
import java.lang.ClassCastException

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    private val TAG: String = "MainFragment"
    lateinit var viewModel: MainViewModel
    lateinit var dataStateListner:DataStateListner
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=activity?.let {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?:throw Exception("Invalid Activity")
        secribeObserver()
    }
    fun secribeObserver(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer {dataState->
            Log.e(TAG, "secribeObserver: $dataState")
            //handle loading and message in activity
            dataStateListner.onDataStateChange(dataState)

            dataState.data?.let {
                mainViewState ->
                mainViewState.blogPost?.let {
                    viewModel.setBlogListData(it)

                }
                mainViewState.user?.let {
                    viewModel.setUser(it)

                }
            }

        })
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            viewState->
            viewState?.blogPost?.let {
                Log.e(TAG, "secribeObserver viewstate blogpost: $it");
            }

            viewState?.user?.let {
                Log.e(TAG, "secribeObserver viewstate user: $it");
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.getuser-> triggeredGetUserEvent()

            R.id.getblog->triggeredGetBlogEvent()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }
    private fun triggeredGetBlogEvent() {
    viewModel.setStateEvent(MainStateEvent.GetBlogPostEvent())
    }

    private fun triggeredGetUserEvent() {
    viewModel.setStateEvent(MainStateEvent.GetUserEvent("1"))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    try {
        dataStateListner= context as DataStateListner

    }catch (e:ClassCastException)
    {
        println("Debug $context must be implement DataStateListner")
    }
    }
}
