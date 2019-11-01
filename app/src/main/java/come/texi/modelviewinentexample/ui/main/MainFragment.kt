package come.texi.modelviewinentexample.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import come.texi.modelviewinentexample.R

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    private val TAG: String = "MainFragment"
    lateinit var viewModel: MainViewModel
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
            dataState.blogPost?.let {

            }
            dataState.user?.let {

            }
        })
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            viewState->
            viewState?.blogPost?.let {
                Log.e(TAG, "secribeObserver: $it");
            }
        })
    }
}
