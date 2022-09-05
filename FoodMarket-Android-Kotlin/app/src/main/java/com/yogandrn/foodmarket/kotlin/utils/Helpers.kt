package com.yogandrn.foodmarket.kotlin.utils

import com.google.gson.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class Helpers {

    fun formatRupiah(number: Int): String{
//        val localeID =  Locale("in", "ID")
//        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
//        return numberFormat.format(number).toString()
        val format = DecimalFormat("#,###,###")
        return "Rp " + format.format(number).replace(",".toRegex(), ".")
    }

    fun getDefaultGson() : Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .registerTypeAdapter(Date::class.java, JsonDeserializer{ json, _, _, ->
                val formatServer = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
                formatServer.timeZone = TimeZone.getTimeZone("UTC")
                formatServer.parse(json.asString)
            })
            .registerTypeAdapter(Date::class.java, JsonSerializer<Date> { src, _, _, ->
                val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
                format.timeZone = TimeZone.getTimeZone("UTC")
                if (src != null) {
                    JsonPrimitive(format.format(src))
                } else {
                    null
                }
            }).create()
    }

    fun convertToTime(date:Long, format:String) : String {
        val date = Date(date)
        val formatTanggal = SimpleDateFormat(format)
        return formatTanggal.format(date)
    }
}
