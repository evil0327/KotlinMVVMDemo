package softocean.app.kotlinmvvm.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment;
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.*
import softocean.app.kotlinmvvm.R
import softocean.app.kotlinmvvm.di.DaggerComponentHolder
import softocean.app.kotlinmvvm.util.GlideApp
import softocean.app.kotlinmvvm.vm.MainViewModel
import softocean.app.kotlinmvvm.vo.City
import javax.inject.Inject

class MainFragment :Fragment() {
    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    @VisibleForTesting
    var viewModel : MainViewModel? = null

    private var cityList = ArrayList<City>()
    private var adapter = MyAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(activity).inflate(R.layout.fragment_main, null)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        DaggerComponentHolder.appComponent!!.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter;

        if(viewModel==null){
            viewModel = ViewModelProviders.of(MainFragment@this, viewModelFactory).get( MainViewModel::class.java)
        }

        viewModel?.getCityLiveData()?.observe(this,
            Observer<List<City>> { cities ->
                loading.visibility = GONE
                if (cities != null && cities.size > 0) {
                    cityList.addAll(cities)
                    adapter.notifyDataSetChanged()
                }
            })
        viewModel?.getToastMsgLiveData()?.observe(this, Observer { msg ->
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        })

        viewModel?.getLoadingShowLiveData()?.observe(this, Observer { isShow ->
            loading.visibility = if(isShow) VISIBLE else GONE
        })

    }

    inner class MyAdapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(activity)
                .inflate(R.layout.view_list_row, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val city = cityList.get(position)
            holder.descView.setText(city.description)
            holder.titleView.setText(city.name)

            GlideApp.with(this@MainFragment)
                .load(city.img)
                .fitCenter()
                .into(holder.imageView)
        }

        override fun getItemCount(): Int {
            return cityList.size
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.image)
        var titleView: TextView = itemView.findViewById(R.id.text)
        var descView: TextView = itemView.findViewById(R.id.desc)
    }
}