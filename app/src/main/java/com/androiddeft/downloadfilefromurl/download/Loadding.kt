package com.androiddeft.downloadfilefromurl.download

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import android.widget.Toast
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

  class Loadding(val context: Context) : AsyncTask<String, String, String>() {

    companion object{
        val TAG ="DownloadFile"
    }
    private var progressDialog: ProgressDialog? = null
    private var fileName: String? = null
    private var folder: String? = null
    private val isDownloaded: Boolean = false

    override fun onPreExecute() {
        super.onPreExecute()
        this.progressDialog = ProgressDialog(context)
        this.progressDialog!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        this.progressDialog!!.setCancelable(false)
        this.progressDialog!!.show()
    }

    override fun doInBackground(vararg f_url: String): String {
        var count: Int
        try {
            val url = URL(f_url[0])
            val connection = url.openConnection()
            connection.connect()
            // getting file length
            val lengthOfFile = connection.contentLength

            val input = BufferedInputStream(url.openStream(), 8192)

            val timestamp = SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Date())

            fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length)
            fileName = timestamp + "_" + fileName

            folder = Environment.getExternalStorageDirectory().toString() + File.separator + "androiddeft/"

            val directory = File(folder!!)

            if (!directory.exists()) {
                directory.mkdirs()
            }

            val output = FileOutputStream(folder!! + fileName!!)

            val data = ByteArray(1024)

            var total: Long = 0
            count = input.read(data)
            while (count != -1) {
                total += count.toLong()

                publishProgress("" + (total * 100 / lengthOfFile).toInt())
                Log.d(TAG, "Progress: " + (total * 100 / lengthOfFile).toInt())

                output.write(data, 0, count)
            }

            output.flush()

            output.close()
            input.close()
            return "Downloaded at: $folder$fileName"

        } catch (e: Exception) {
            Log.e("Error: ", e.message)
        }

        return null.toString()
    }

    override fun onProgressUpdate(vararg progress: String) {
        progressDialog!!.progress = Integer.parseInt(progress[0])
    }


    override fun onPostExecute(message: String) {

        this.progressDialog!!.dismiss()


    }
}