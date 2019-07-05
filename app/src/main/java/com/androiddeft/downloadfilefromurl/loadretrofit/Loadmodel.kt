package com.androiddeft.downloadfilefromurl.loadretrofit

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import android.webkit.WebView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.bookhotels.websecvice.Client
import com.example.dowloadvideo.common.Constant
import okhttp3.ResponseBody
import org.json.JSONArray
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.w3c.dom.Document
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import java.net.URL

class Loadmodel(val context: Context) : AsyncTask<String, Int, Void>() {

    companion object {
        val TAG = "Loadmodel"
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

    override fun doInBackground(vararg p0: String?): Void? {
        val url = p0[0]
        if (url != null) {
            loadDocument(url)
        }
        return null
    }

    fun loadDocument(urlstring: String) {
        val queue = Volley.newRequestQueue(context)
        val url: String = Constant.BASE_URL + "download/?video=https://www.facebook.com/DailyViralEpisode/videos/693684781081758/"

        val stringReq = StringRequest(Request.Method.GET, url,
                com.android.volley.Response.Listener<String> { response ->
                    try {
                        val document: org.jsoup.nodes.Document? = Jsoup.parse(response)
                        if (document != null) {
                            val table = document.select("table[class=downloadsTable]").first()
                            val tbody = table.select("tbody")
                            val rows = tbody.select("tr")

                            for (i in 0 until rows.size) {
                                val row = rows[i]
                                val td = row.select("td")
                                val a = td[3].select("a")
                                val link = a.attr("href")
                                if (td[0].text().equals("HD (Mirror)")) {
                                    Log.d("TD_ONE", "" + link)
                                }
                                if (td[0].text().equals("SD (Mirror)")) {
                                    Log.d(TAG, "" + td[0].text())
                                    Log.d(TAG, "" + link)
                                }
                            }
                        } else {
                            Log.d(LoadActivity.TAG, "Err...")
                        }

                    } catch (io: IOException) {
                        Log.d(LoadActivity.TAG, "" + io.message)
                    }
                },
                com.android.volley.Response.ErrorListener { })
        queue.add(stringReq)
    }

}

