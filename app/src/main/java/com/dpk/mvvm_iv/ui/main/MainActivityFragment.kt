package com.dpk.mvvm_iv.ui.main

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dpk.mvvm_iv.R
import com.dpk.mvvm_iv.databinding.MainfragmentBinder
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {
    lateinit var mainImp: MainFragmentInterface
    lateinit var binding: MainfragmentBinder
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_main, container, false)
        binding.vm = MainFragmentViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //监听检验变量的变化
        binding.vm?.issuccess?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                //不是默认值的话请求验车
                if (binding.vm?.issuccess?.get()!!)
                    mainImp.inspectionStatus(binding.vm?.ispass?.get()!!)
            }

        })

        et_listener.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.vm?.listener = et_listener.text.toString()
            }

        })
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        //show时设置为默认状态
//        if (!hidden)
//            binding.vm?.issuccess?.set(true)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        mainImp = activity as MainActivity
    }

    interface MainFragmentInterface {
        fun inspectionStatus(status: Boolean)
    }
}
