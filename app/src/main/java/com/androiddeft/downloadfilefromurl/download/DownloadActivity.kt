package com.androiddeft.downloadfilefromurl.download

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.androiddeft.downloadfilefromurl.R
import kotlinx.android.synthetic.main.activity_download.*
import android.net.Uri


class DownloadActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        val duongdan = edtUrl.text.toString()
        val vidurl = Uri.parse(duongdan)
        videoView.setVideoURI(vidurl)
        videoView.start()
        btnDownload.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.btnDownload ->{
                val duongdan = edtUrl.text.toString()
                download(duongdan)
            }
        }
    }
    private fun download(duongdan:String){
        val DB= Downback(this)
        DB.execute(duongdan)
    }

}
