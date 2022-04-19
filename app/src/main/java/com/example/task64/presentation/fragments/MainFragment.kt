package com.example.task64.presentation.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task64.data.repositories.ImagesRepository
import com.example.task64.databinding.FragmentMainBinding
import com.example.task64.presentation.viewmodel.mainFragmentvm.MainFragViewModel
import com.example.task64.presentation.viewmodel.mainFragmentvm.MainFragVmFactory
import example.task64.adapter.ImageAdapter
import com.example.task64.data.remote.ApiInterface
import dagger.hilt.android.AndroidEntryPoint
import dmax.dialog.SpotsDialog

class MainFragment : Fragment() {

    lateinit var mainFragViewModel: MainFragViewModel
    private lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var dialog: AlertDialog
    private lateinit var adapter: ImageAdapter
    private lateinit var recyclerView: RecyclerView
    private val apiInterface = ApiInterface.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("jok" , "Frag created")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater , container , false)
        return fragmentMainBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = fragmentMainBinding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this.requireContext(), 2)
        adapter = ImageAdapter(this.requireContext())
        recyclerView.adapter = adapter

        dialog = SpotsDialog.Builder().setContext(requireContext()).build()

        mainFragViewModel =ViewModelProvider(requireActivity(), MainFragVmFactory(ImagesRepository(apiInterface)))
                .get(MainFragViewModel::class.java)

        mainFragViewModel.imagesList.observe(this, Observer {
            adapter.imagesList = it.toMutableList()
            adapter.notifyDataSetChanged()
        })
        mainFragViewModel.getAllImages()

    }



}