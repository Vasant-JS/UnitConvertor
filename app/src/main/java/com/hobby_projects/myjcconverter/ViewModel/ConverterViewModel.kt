package com.hobby_projects.myjcconverter.ViewModel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.hobby_projects.myjcconverter.ConverterAction
import com.hobby_projects.myjcconverter.ConverterScreen

class ConverterViewModel() : ViewModel() {
    var context: Context ?= null
    var typeId = 0;

    fun onAction(action: ConverterAction) {
        when (action) {
            is ConverterAction.Currency -> convertCurrency()
            is ConverterAction.Length -> convertLength()
            is ConverterAction.Area -> convertArea()
            is ConverterAction.Volume -> convertVolume()
            is ConverterAction.Weight -> convertWeight()
            is ConverterAction.Temperature -> convertTemperature()
            is ConverterAction.Speed -> convertSpeed()
            is ConverterAction.Pressure -> convertPressure()
            is ConverterAction.Power -> convertPower()
        }
    }

    private fun convertCurrency() {
        typeId = 1
        gotoNextPage()
    }
    private fun convertLength(){
        typeId = 2
        gotoNextPage()
    }

    private fun convertArea(){
        typeId = 3
        gotoNextPage()
    }
    private fun convertVolume(){
        typeId = 4
        gotoNextPage()
    }
    private fun convertWeight(){
        typeId = 5
        gotoNextPage()
    }
    private fun convertTemperature(){
        typeId = 6
        gotoNextPage()
    }
    private fun convertSpeed(){
        typeId = 7
        gotoNextPage()
    }
    private fun convertPressure(){
        typeId = 8
        gotoNextPage()
    }
    private fun convertPower(){
        typeId = 9
        gotoNextPage()
    }


    private fun gotoNextPage() {
        val intent = Intent(context, ConverterScreen::class.java)
        intent.putExtra("typeId", typeId)
        context?.startActivity(intent)
    }

    @JvmName("setContext1")
    fun setContext(newContext: Context) {
        this.context = newContext
//        Toast.makeText(context, "Context set"+context, Toast.LENGTH_SHORT).show()

    }

}








