package com.example.pelicudex

import android.net.Uri
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.util.*
import javax.net.ssl.HttpsURLConnection

class NetworkUtils {
    val MOVIES_API_BASEURL = "http://omdbapi.com/"
    val TOKEN_API = "7399a5f5"

    fun buildSearchUrl(movieName:String):URL{
        val builtUrl= Uri.parse(MOVIES_API_BASEURL)
                .buildUpon()
                .appendQueryParameter("apikey",TOKEN_API)
                .appendQueryParameter("t",movieName)
                .build()
        return try {
            URL(builtUrl.toString())
        }catch (e: MalformedURLException){
            URL("")
        }
    }

    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url:URL):String{
        val urlConnection = url.openConnection() as HttpsURLConnection
        try {
            val `in` = urlConnection.inputStream
            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()
            return if(hasInput){
                scanner.next()
            }else{
                ""
            }
        }finally {
            urlConnection.disconnect()
        }
    }

}