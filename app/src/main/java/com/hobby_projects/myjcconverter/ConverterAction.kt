package com.hobby_projects.myjcconverter

sealed class ConverterAction {
    data class UnitType(val unitType: String): ConverterAction()
    data class UnitValue(val unitValue: Double): ConverterAction()
    object Currency: ConverterAction()
    object Length: ConverterAction()
    object Area: ConverterAction()
    object Volume: ConverterAction()
    object Weight: ConverterAction()
    object Temperature: ConverterAction()
    object Speed: ConverterAction()
    object Pressure: ConverterAction()
    object Power: ConverterAction()
}
