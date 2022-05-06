package com.cwramirezg.tipocambio.data.source.local

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {
    @TypeConverter
    fun fromBigDecimal(value: Double): BigDecimal = BigDecimal.valueOf(value)

    @TypeConverter
    fun bigDecimalToDouble(bigDecimal: BigDecimal): Double = bigDecimal.toDouble()

}