package com.hobby_projects.myjcconverter.ViewModel

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hobby_projects.myjcconverter.Model.*

class CalculateViewModel(val context: Context, val typeId: Int) : ViewModel() {
//    var context:Context? by mutableStateOf(null)
//    var typeId by mutableStateOf(0)

    private val listOfCurrency = listOf("INDIAN_RUPEE", "US_DOLLAR", "EURO", "BRITISH_POUND", "AUSTRALIAN_DOLLAR", "CANADIAN_DOLLAR", "SINGAPORE_DOLLAR", "SWISS_FRANC", "MALAYSIAN_RINGGIT", "JAPANESE_YEN", "CHINESE_YUAN_RENMINBI")
    private val listOfLength = listOf( "METER", "KILOMETER", "DECIMETER", "CENTIMETER", "MILLIMETER", "MICROMETER", "YARD", "MILE", "INCH", "FOOT", "FURLONG")
    private val listOfArea = listOf("SQ_METER", "ARE", "HECTARE", "SQ_KILO_METER", "SQ_DECIMETER", "SQ_CENTIMETER", "SQ_MILLIMETER", "ACRE", "SQ_MILE", "SQ_YARD", "SQ_FOOT", "SQ_INCH", "SQ_ROD")
    private val listOfVolume = listOf("CUBIC_DECIMETRE", "CUBIC_METRE", "HECTO_LITRE", "CUBIC_CENTIMETRE", "CUBIC_MILLIMETRE", "LITRE", "DECI_LITRE", "CENTI_LITRE", "MILLI_LITRE", "CUBIC_FOOT", "CUBIC_INCH", "CUBIC_YARD", "ACRE_FOOT", "UK_GALLON", "US_GALLON", "UK_FLUID_OUNCE", "US_FLUID_OUNCE")
    private val listOfWeight = listOf("KILO_GRAM", "GRAM", "MILLI_GRAM", "MICRO_GRAM", "TONNE", "QUINTAL", "CARAT", "POUND", "OUNCE", "GRAIN", "LONG_TON", "SHORT_TON", "LONG_HUNDRED_WEIGHT", "SHORT_HUNDRED_WEIGHT", "STONE", "DRAM")
    private val listOfTemperature = listOf("DEGREE_CELSIUS", "DEGREE_FAHRENHEIT", "DEGREE_REAUMUR", "DEGREE_RANKINE", "KELVIN")
    private val listOfSpeed = listOf("METRE/SECOND", "KILOMETRE/SECOND", "KILOMETRE/HOUR", "SPEED_OF_LIGHT", "MILE/HOUR", "MACH", "INCH/SECOND")
    private val listOfPressure = listOf("STANDARD_ATMOSPHERE", "HECTOPASCAL", "KILOPASCAL", "MEGAPASCAL", "MILLIMETRE_OF_MERCURY", "INCH_OF_MERCURY", "BAR", "MILLIBAR", "POUNDS/SQUARE_INCH", "POUNDS/SQUARE_FOOT", "KILOGRAM_FORCE/SQUARE_METRE", "KILOGRAM_FORCE/SQUARE_CENTIMETRE", "MILLIMETRE_OF_WATER")
    private val listOfPower = listOf("KILOWATT", "WATT", "JOULE/SECOND", "IMPERIAL_HORSEPOWER", "METRIC_HORSEPOWER", "KILOGRAM_METRE/SECOND", "KILOCALORIE/SECOND", "BRITISH_THERMAL_UNIT/SECOND", "FOOT_POUND/SECOND", "NEWTON_METRE/SECOND")

    private val listOfLengthMeasurements = listOf("m", "km", "dm", "cm", "mm", "(Mu)m", "yard", "mi", "in", "ft", "fur")
    private val listOfCurrencyMeasurements = listOf("INR", "USB", "EUR", "GBP", "AUD", "CAD", "SGD", "CHF", "MYR", "JPY", "CNY")
    private val listOfAreaMeasurements = listOf("m(2)", "ar", "ha", "km(2)", "dm(2)", "cm(2)", "mm(2)", "ac", "mi(2)", "yd(2)", "ft(2)", "in(2)", "rd(2)")
    private val listOfVolumeMeasurements = listOf("dm(3)", "m(3)", "hl", "cm(3)", "mm(3)", "1", "dl", "cl", "ml", "ft(3)", "in(3)", "yd(3)", "af(3)", "UK gal", "US gal", "UK fl oz", "US fl oz")
    private val listOfWeightMeasurements = listOf("kg", "g", "mg", "µg", "t", "q", "ct", "lb", "oz", "gr", "lt", "st", "Ig_cwt", "sh_cwt", "st", "dr")
    private val listOfTemperatureMeasurements = listOf("(°)C", "(°)F", "(°)Re", "(°)R", "K")
    private val listOfSpeedMeasurements = listOf("m/s", "km/s", "km/h", "c", "mph", "Ma", "in/s")
    private val listOfPressureMeasurements = listOf("atm", "hPa", "kPa", "MPa", "mmHg", "inHg", "bar", "mbar", "psi", "psf", "kgf/m(2)", "kgf/cm(2)", "mmH20")
    private val listOfPowerMeasurements = listOf("kW", "W", "J/s", "hp", "PS", "kg m/s", "kcal/s", "Btu/s", "ft.lb/s", "Nm/s")

//    var listOfUnits by mutableStateOf(listOfCurrency) //Default Values
//    var listOfMeasurementUnits by mutableStateOf(listOfCurrencyMeasurements) //Default Values
    var listOfUnits =
    when (typeId) {
        1 -> listOfCurrency
        2 -> listOfLength
        3 -> listOfArea
        4 -> listOfVolume
        5 -> listOfWeight
        6 -> listOfTemperature
        7 -> listOfSpeed
        8 -> listOfPressure
        9 -> listOfPower
        else -> listOfArea
    }

    var listOfMeasurementUnits =
    when (typeId) {
        1 -> listOfCurrencyMeasurements
        2 -> listOfLengthMeasurements
        3 -> listOfAreaMeasurements
        4 -> listOfVolumeMeasurements
        5 -> listOfWeightMeasurements
        6 -> listOfTemperatureMeasurements
        7 -> listOfSpeedMeasurements
        8 -> listOfPressureMeasurements
        9 -> listOfPowerMeasurements
        else -> listOfAreaMeasurements
    }

    var unit1 by mutableStateOf(listOfUnits[0])
    var unit2 by mutableStateOf(listOfUnits[1])

    var unit1Value by mutableStateOf("")
    var unit2Value by mutableStateOf("")

    var unit1Units by mutableStateOf(listOfMeasurementUnits[0])
    var unit2Units by mutableStateOf(listOfMeasurementUnits[1])

    var history = mutableListOf<String>()

    fun unit1Changed() {
        unit1Units = listOfMeasurementUnits[listOfUnits.indexOf(unit1)]
        unit1ValueChanged(unit1Value)
    }

    fun unit2Changed() {
        unit2Units = listOfMeasurementUnits[listOfUnits.indexOf(unit2)]
        unit2ValueChanged(unit2Value)
    }

    fun unit1ValueChanged(newValue: String): String {
        unit2Value =
            ((getInBaseUnit(unit2) * (if (newValue == "") 0.0 else newValue.toDouble())) / getInBaseUnit(
                unit1
            )).toString()
        unit2Value = updatedResult(unit2Value)
        addHistory(newValue, unit1, unit2Value, unit2)
        return newValue
    }

    fun unit2ValueChanged(newValue: String): String {
        unit1Value =
            ((getInBaseUnit(unit1) * (if (newValue == "") 0.0 else newValue.toDouble())) / getInBaseUnit(
                unit2
            )).toString()
        unit1Value = updatedResult(unit1Value)
        addHistory(unit1Value, unit1, newValue, unit2)
        return newValue
    }

    private fun addHistory(u1v: String, u1: String, u2v: String, u2: String) {
        if (u1v.isNotBlank() && u1v != "0.0" && u2v.isNotBlank() && u2v != "0.0")
            history.add("$u1v $u1 = $u2v $u2")
    }

    private fun getInBaseUnit(unit: String): Double {
        return when (typeId) {
            1 -> getInINR(unit)
            2 -> getInMeters(unit)
            3 -> getInSqMeters(unit)
            4 -> getInCubicDeciMeter(unit)
            5 -> getInKilograms(unit)
            6 -> getInDegreeCelsius(unit)
            7 -> getInMeterPerSecond(unit)
            8 -> getInStandardAtmosphere(unit)
            9 -> getInKiloWatt(unit)
            else -> getInSqMeters(unit)
        }
    }

    private fun getInMeters(unit: String): Double {
        return when (unit) {
            "METER" -> Length.METER
            "KILOMETER" -> Length.KILOMETER
            "DECIMETER" -> Length.DECIMETER
            "CENTIMETER" -> Length.CENTIMETER
            "MILLIMETER" -> Length.MILLIMETER
            "MICROMETER" -> Length.MICROMETER
            "YARD" -> Length.YARD
            "MILE" -> Length.MILE
            "INCH" -> Length.INCH
            "FOOT" -> Length.FOOT
            "FURLONG" -> Length.FURLONG
            else -> 0.0
        }
    }

    private fun getInINR(unit: String): Double {
        return when (unit) {
            "INDIAN_RUPEE" -> Currency.INDIAN_RUPEE
            "US_DOLLAR" -> Currency.US_DOLLAR
            "EURO" -> Currency.EURO
            "BRITISH_POUND" -> Currency.BRITISH_POUND
            "AUSTRALIAN_DOLLAR" -> Currency.AUSTRALIAN_DOLLAR
            "CANADIAN_DOLLAR" -> Currency.CANADIAN_DOLLAR
            "SINGAPORE_DOLLAR" -> Currency.SINGAPORE_DOLLAR
            "SWISS_FRANC" -> Currency.SWISS_FRANC
            "MALAYSIAN_RINGGIT" -> Currency.MALAYSIAN_RINGGIT
            "JAPANESE_YEN" -> Currency.JAPANESE_YEN
            "CHINESE_YUAN_RENMINBI" -> Currency.CHINESE_YUAN_RENMINBI
            else -> 0.0
        }
    }

    private fun getInSqMeters(unit: String): Double {
        return when (unit) {
            "SQ_METER" -> Area.SQ_METER
            "ARE" -> Area.ARE
            "HECTARE" -> Area.HECTARE
            "SQ_KILO_METER" -> Area.SQ_KILO_METER
            "SQ_DECIMETER" -> Area.SQ_DECIMETER
            "SQ_CENTIMETER" -> Area.SQ_CENTIMETER
            "SQ_MILLIMETER" -> Area.SQ_MILLIMETER
            "ACRE" -> Area.ACRE
            "SQ_MILE" -> Area.SQ_MILE
            "SQ_YARD" -> Area.SQ_YARD
            "SQ_FOOT" -> Area.SQ_FOOT
            "SQ_INCH" -> Area.SQ_INCH
            "SQ_ROD" -> Area.SQ_ROD
            else -> 0.0
        }
    }

    private fun getInCubicDeciMeter(unit: String): Double{
        return when(unit){
            "CUBIC_DECIMETRE" -> Volume.CUBIC_DECIMETRE
            "CUBIC_METRE" -> Volume.CUBIC_METRE
            "HECTO_LITRE" -> Volume.HECTO_LITRE
            "CUBIC_CENTIMETRE" -> Volume.CUBIC_CENTIMETRE
            "CUBIC_MILLIMETRE" -> Volume.CUBIC_MILLIMETRE
            "LITRE" -> Volume.LITRE
            "DECI_LITRE" -> Volume.DECI_LITRE
            "CENTI_LITRE" -> Volume.CENTI_LITRE
            "MILLI_LITRE" -> Volume.MILLI_LITRE
            "CUBIC_FOOT" -> Volume.CUBIC_FOOT
            "CUBIC_INCH" -> Volume.CUBIC_INCH
            "CUBIC_YARD" -> Volume.CUBIC_YARD
            "ACRE_FOOT" -> Volume.ACRE_FOOT
            "UK_GALLON" -> Volume.UK_GALLON
            "US_GALLON" -> Volume.US_GALLON
            "UK_FLUID_OUNCE" -> Volume.UK_FLUID_OUNCE
            "US_FLUID_OUNCE" -> Volume.US_FLUID_OUNCE
            else -> 0.0
        }
    }

    private fun getInKilograms(unit: String): Double{
        return when(unit){
            "KILO_GRAM" -> Weight.KILO_GRAM
            "GRAM" -> Weight.GRAM
            "MILLI_GRAM" -> Weight.MILLI_GRAM
            "MICRO_GRAM" -> Weight.MICRO_GRAM
            "TONNE" -> Weight.TONNE
            "QUINTAL" -> Weight.QUINTAL
            "CARAT" -> Weight.CARAT
            "POUND" -> Weight.POUND
            "OUNCE" -> Weight.OUNCE
            "GRAIN" -> Weight.GRAIN
            "LONG_TON" -> Weight.LONG_TON
            "SHORT_TON" -> Weight.SHORT_TON
            "LONG_HUNDRED_WEIGHT" -> Weight.LONG_HUNDRED_WEIGHT
            "SHORT_HUNDRED_WEIGHT" -> Weight.SHORT_HUNDRED_WEIGHT
            "STONE" -> Weight.STONE
            "DRAM" -> Weight.DRAM
            else -> 0.0
        }
    }

    private fun getInDegreeCelsius(unit: String): Double {
        return when(unit){
            "DEGREE_CELSIUS" -> Temperature.DEGREE_CELSIUS
            "DEGREE_FAHRENHEIT" -> Temperature.DEGREE_FAHRENHEIT
            "DEGREE_REAUMUR" -> Temperature.DEGREE_REAUMUR
            "DEGREE_RANKINE" -> Temperature.DEGREE_RANKINE
            "KELVIN" -> Temperature.KELVIN
            else -> 0.0
        }
    }

    private fun getInMeterPerSecond(unit: String): Double {
        return when(unit){
            "METRE/SECOND" -> Speed.METRE_per_SECOND
            "KILOMETRE/SECOND" -> Speed.KILOMETRE_per_SECOND
            "KILOMETRE/HOUR" -> Speed.KILOMETRE_per_HOUR
            "SPEED_OF_LIGHT" -> Speed.SPEED_OF_LIGHT
            "MILE/HOUR" -> Speed.MILE_per_HOUR
            "MACH" -> Speed.MACH
            "INCH/SECOND" -> Speed.INCH_per_SECOND
            else -> 0.0
        }
    }

    private fun getInStandardAtmosphere(unit: String): Double {
        return when(unit){
            "STANDARD_ATMOSPHERE" -> Pressure.STANDARD_ATMOSPHERE
            "HECTOPASCAL" -> Pressure.HECTOPASCAL
            "KILOPASCAL" -> Pressure.KILOPASCAL
            "MEGAPASCAL" -> Pressure.MEGAPASCAL
            "MILLIMETRE_OF_MERCURY" -> Pressure.MILLIMETRE_OF_MERCURY
            "INCH_OF_MERCURY" -> Pressure.INCH_OF_MERCURY
            "BAR" -> Pressure.BAR
            "MILLIBAR" -> Pressure.MILLIBAR
            "POUNDS/SQUARE_INCH" -> Pressure.POUNDS_PER_SQUARE_INCH
            "POUNDS/SQUARE_FOOT" -> Pressure.POUNDS_PER_SQUARE_FOOT
            "KILOGRAM_FORCE/SQUARE_METRE" -> Pressure.KILOGRAM_FORCE_PER_SQUARE_METRE
            "KILOGRAM_FORCE/SQUARE_CENTIMETRE" -> Pressure.KILOGRAM_FORCE_PER_SQUARE_CENTIMETRE
            "MILLIMETRE_OF_WATER" -> Pressure.MILLIMETRE_OF_WATER
            else -> 0.0
        }
    }


    private fun getInKiloWatt(unit: String): Double {
        return when(unit){
            "KILOWATT" -> Power.KILOWATT
            "WATT" -> Power.WATT
            "JOULE/SECOND" -> Power.JOULE_PER_SECOND
            "IMPERIAL_HORSEPOWER" -> Power.IMPERIAL_HORSEPOWER
            "METRIC_HORSEPOWER" -> Power.METRIC_HORSEPOWER
            "KILOGRAM_METRE/SECOND" -> Power.KILOGRAM_METRE_PER_SECOND
            "KILOCALORIE/SECOND" -> Power.KILOCALORIE_PER_SECOND
            "BRITISH_THERMAL_UNIT/SECOND" -> Power.BRITISH_THERMAL_UNIT_PER_SECOND
            "FOOT_POUND/SECOND" -> Power.FOOT_POUND_PER_SECOND
            "NEWTON_METRE/SECOND" -> Power.NEWTON_METRE_PER_SECOND
            else -> 0.0
        }
    }

    fun finishActivity() {
        (context as Activity).finish()
    }

    fun popUpToast(label: String) {
        Toast.makeText(
            context,
            "label: $label",
            Toast.LENGTH_SHORT
        ).show()

    }

    private fun updatedResult(value: String): String {
        if(!value.contains("E")){
            if(value.contains(".")){
                return value.substringBefore(".")+"."+value.substringAfter(".").take(2)
            }
        }
        return value
    }
}


//Universal Function
inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(aClass: Class<T>):T = f() as T
    }