package com.dpk.mvvm_iv.ui.other

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dpk.mvvm_iv.R
import com.dpk.mvvm_iv.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_inspection_result.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast

class InspectionResultActivity : Fragment() {
    lateinit var mainImp: MainActivity
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button2.onClick { mainImp.back() }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.activity_inspection_result, container, false)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden){
            if (arguments != null)
                toast( arguments.getBoolean("status").toString())
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        mainImp = activity as MainActivity
    }

    interface InspectionInterface {
        fun back()
    }
}