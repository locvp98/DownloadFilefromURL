package com.androiddeft.downloadfilefromurl.download

import android.os.AsyncTask
import android.os.Environment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import android.system.Os.mkdir
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import android.app.ProgressDialog
import android.content.Context
import android.util.Log


class Downback(val context: Context) : AsyncTask<String, String, String>() {
    companion object{
        val TAG ="Downback"
    }
    private var progressDialog: ProgressDialog? = null
    var count: Int = 0
    override fun doInBackground(vararg p0: String?): String {
        var vidurl: String? = p0[0]
        if (vidurl != null) {
            downloadfile(vidurl)
        }
        return null.toString()
    }

    override fun onPreExecute() {
        super.onPreExecute()
        this.progressDialog = ProgressDialog(context)
        this.progressDialog!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        this.progressDialog!!.setCancelable(false)
        this.progressDialog!!.show()
    }



    private fun downloadfile(url: String) {
        val sd = SimpleDateFormat("yymmhh")
        val date: String = sd.format(Date())
        val name = "video$date.mp4"
        try {
            val rootDir = (Environment.getExternalStorageDirectory().toString()
                    + File.separator + "Video")
            val rootFile = File(rootDir)
            rootFile.mkdir()
            val url = URL(url)
            val c = url.openConnection() as HttpURLConnection
            val lengthOfFile = c.contentLength
            c.requestMethod = "GET"
            c.connect()
            val fileoutput = FileOutputStream(
                    File(
                            rootFile,
                            name
                    )
            )
            val inputstream = c.inputStream
            val buffer = ByteArray(1024)
            var total: Long = 0
            count = inputstream.read()
            while (count != -1) {
                total += count.toLong()
                publishProgress("" + (total * 100 / lengthOfFile).toInt())
                Log.d(TAG, "Progress: " + (total * 100 / lengthOfFile).toInt())
                fileoutput.write(buffer, 0, count)
            }
            fileoutput.flush()
            fileoutput.close()
            inputstream.close()

        } catch (e: IOException) {
        }

    }
    override fun onProgressUpdate(vararg values: String?) {
        super.onProgressUpdate(*values)
        progressDialog!!.progress = Integer.parseInt(values[0])
    }

    fun dowload(){

    }
}