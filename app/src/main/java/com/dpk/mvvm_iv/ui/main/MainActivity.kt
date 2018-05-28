package com.dpk.mvvm_iv.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dpk.mvvm_iv.R
import com.dpk.mvvm_iv.ui.other.InspectionResultActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityFragment.MainFragmentInterface, InspectionResultActivity.InspectionInterface {
    override fun back() {
        supportFragmentManager.beginTransaction().hide(fragment1).show(fragment).commit()
    }

    override fun inspectionStatus(status: Boolean) {
        fragment1.arguments = Bundle().apply { putBoolean("status", status) }
        supportFragmentManager.beginTransaction().hide(fragment).show(fragment1).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().hide(fragment1).commit()
    }

}
