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
        binding.vm?.issuccess?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (binding.vm?.issuccess?.get() != binding.vm?.DEF)
                    mainImp.inspectionStatus(binding.vm?.ispass!!)
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
        if (!hidden)
            binding.vm?.issuccess?.set(binding.vm?.DEF!!)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        mainImp = activity as MainActivity
    }

    interface MainFragmentInterface {
        fun inspectionStatus(status: Boolean)
    }
}
