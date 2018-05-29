package com.example.ayushgupta.ktmy8.presenter

import com.example.ayushgupta.ktmy8.Beans.IncExpPojo

interface IncExpPresenterAPI{
    fun addInput(pojo:IncExpPojo)
    fun addIncInput()
    fun addExpInput()
    fun addSummInput()
}