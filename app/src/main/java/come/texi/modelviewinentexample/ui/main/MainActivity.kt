package come.texi.modelviewinentexample.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import come.texi.modelviewinentexample.R
import come.texi.modelviewinentexample.ui.DataStateListner
import come.texi.modelviewinentexample.utils.DataState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),DataStateListner {
    override fun onDataStateChange(dataState: DataState<*>?) {
        handleDataStateChange(dataState)
    }

    private fun handleDataStateChange(dataState: DataState<*>?) {
        dataState?.let {

            it.message?.let { message ->
               showToast(message)
            }
            it.laoding?.let {
                showProgressBar(it)
            }
        }
    }

    fun showProgressBar(isVisible:Boolean)
    {
        if(isVisible)
        {
            progress_bar.visibility= View.VISIBLE
        }else
        {
            progress_bar.visibility=View.INVISIBLE
        }
    }
    fun showToast(message:String)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel=ViewModelProvider(this).get(MainViewModel::class.java)
        showMainFragmemnt()
    }

    private fun showMainFragmemnt() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame,MainFragment())
            .commit()

    }


}
