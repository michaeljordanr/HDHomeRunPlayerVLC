package com.vlc.sample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity(), VideoListAdapter.VideoClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvVideos = findViewById<RecyclerView>(R.id.rv_videos)

        val videoLinksJson = loadJSONFromAsset(baseContext, "hdhomerun.json")

        val type = object : TypeToken<List<Video>>(){}.type
        val videoList = Gson().fromJson<List<Video>>(videoLinksJson, type)

        val adapter = VideoListAdapter(baseContext, videoList, this)

        rvVideos.layoutManager = LinearLayoutManager(baseContext)
        rvVideos.adapter = adapter
    }

    fun loadJSONFromAsset(c: Context, file: String): String? {
        val json: String
        try {

            val mngr = c.applicationContext.assets
            val `is` = mngr.open(file)

            val size = `is`.available()

            val buffer = ByteArray(size)

            `is`.read(buffer)

            `is`.close()

            json = String(buffer, Charset.forName("UTF-8"))


        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json

    }

    override fun onVideoSelected(video: Video) {
        VideoActivity.start(baseContext, video.uRL)
    }
}