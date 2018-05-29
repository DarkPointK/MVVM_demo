package com.dpk.mvvm_iv.ui.main

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dpk.mvvm_iv.R
import com.dpk.mvvm_iv.databinding.MainfragmentBinder

class MainActivityFragment : Fragment() {
    lateinit var mainImp: MainFragmentInterface
    lateinit var binding: MainfragmentBinder
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = ViewModelProviders.of(this@MainActivityFragment).get(MainFragmentViewModel::class.java)
        binding.setLifecycleOwner(this)
        //监听检验变量的变化
        binding.vm?.ispass?.observe(this@MainActivityFragment, Observer<Boolean> {
            //不是默认值的话请求验车
            if (binding.vm?.issuccess?.value!!)
                mainImp.inspectionStatus(binding.vm?.ispass?.value!!)
        })
    }


    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        mainImp = activity as MainActivity
    }

    interface MainFragmentInterface {
        fun inspectionStatus(status: Boolean)
    }
}
