package com.androiddeft.downloadfilefromurl.loadretrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.androiddeft.downloadfilefromurl.R
import com.example.dowloadvideo.common.Constant
import kotlinx.android.synthetic.main.activity_load.*
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException
import java.net.URL

class LoadActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var loadmodel: Loadmodel
    private var urlstring: String? = null

    companion object {
        const val TAG = "activity_load"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        loadmodel = Loadmodel(this)
        btnDownload.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.btnDownload -> {
                urlstring = edtUrl.text.toString()
                loadmodel.execute(urlstring)
            }
        }
    }


}
