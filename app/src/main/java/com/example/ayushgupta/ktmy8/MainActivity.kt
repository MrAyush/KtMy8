package com.example.ayushgupta.ktmy8

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.ayushgupta.ktmy8.Beans.IncExpPojo
import com.example.ayushgupta.ktmy8.model.IncExpModel
import com.example.ayushgupta.ktmy8.presenter.IncExpPresenterAPI
import com.example.ayushgupta.ktmy8.view.IncExpViewAPI

class MainActivity : AppCompatActivity(), IncExpViewAPI {
    private var sp1: Spinner? = null
    private var et1: EditText? = null
    private var et2: EditText? = null
    private var et3: EditText? = null
    private var lview: ListView? = null
    private var pi: IncExpPresenterAPI? = null

    override fun viewAddResult(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun addViewInc(list: ArrayList<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        lview?.adapter = adapter
    }

    override fun addViewExp(list: ArrayList<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        lview?.adapter = adapter
    }

    override fun addViewSumm(list: ArrayList<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        lview?.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sp1 = findViewById(R.id.sp1)
        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)
        et3 = findViewById(R.id.et3)
        lview = findViewById(R.id.lview)
        pi = IncExpModel(this)
    }

    fun add(v: View) {
        val pojo = IncExpPojo(sp1?.selectedItem.toString(), et1?.text.toString().toInt(), et2?.text.toString(), et3?.text.toString())
        pi?.addInput(pojo)
    }

    fun viewInc(v: View) {
        pi?.addIncInput()
    }

    fun viewExpense(v: View) {
        pi?.addExpInput()
    }

    fun viewSummary(v: View) {
        pi?.addSummInput()
    }
}
