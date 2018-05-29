package com.example.ayushgupta.ktmy8.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.example.ayushgupta.ktmy8.Beans.IncExpPojo
import com.example.ayushgupta.ktmy8.MainActivity
import com.example.ayushgupta.ktmy8.presenter.IncExpPresenterAPI
import com.example.ayushgupta.ktmy8.view.IncExpViewAPI

class IncExpModel : IncExpPresenterAPI {
    private var vi: IncExpViewAPI? = null
    private var dBase: SQLiteDatabase? = null

    constructor(vi: IncExpViewAPI) {
        this.vi = vi
        val mActivity = this.vi as MainActivity
        dBase = mActivity.openOrCreateDatabase("incexp_db", Context.MODE_PRIVATE, null)
        dBase?.execSQL("create table if not exists incexp(type varchar(100),money number, _date varchar(100), description varchar(500))")
        Toast.makeText(mActivity,"Created", Toast.LENGTH_SHORT).show()
    }

    override fun addInput(pojo: IncExpPojo) {
        val c = ContentValues()
        c.put("type", pojo.type)
        c.put("money", pojo.money)
        c.put("_date", pojo._date)
        c.put("description", pojo.desc)
        val status = dBase!!.insert("incexp", null, c)
        if (status == (-1).toLong()) {
            vi?.viewAddResult("Failed to insert")
        } else {
            vi?.viewAddResult("Successfully inserted the record")
        }
    }

    override fun addIncInput() {
        val list: ArrayList<String> = ArrayList()
        val c = dBase?.query("incexp", null, "type=?", arrayOf("Income"), null, null, null)
        while (c!!.moveToNext()) {
            list.add("${c.getString(0)} | ${c.getInt(1)} | ${c.getString(2)} | ${c.getString(3)}")
        }
        vi?.addViewInc(list)

    }

    override fun addExpInput() {
        val list: ArrayList<String> = ArrayList()
        val c = dBase?.query("incexp", null, "type=?", arrayOf("Expense"), null, null, null)
        while (c!!.moveToNext()) {
            list.add("${c.getString(0)} | ${c.getInt(1)} | ${c.getString(2)} | ${c.getString(3)}")
        }
        vi?.addViewInc(list)
    }

    override fun addSummInput() {
        val list: ArrayList<String> = ArrayList()
        val c = dBase?.query("incexp", null, null, null, null, null, null)
        while (c!!.moveToNext()) {
            list.add("${c.getString(0)} | ${c.getInt(1)} | ${c.getString(2)} | ${c.getString(3)}")
        }
        vi?.addViewInc(list)
    }

}