package com.vlc.sample

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import org.videolan.libvlc.util.VLCVideoLayout
import java.util.ArrayList

class VideoActivity : AppCompatActivity() {

    private lateinit var videoLayout: VLCVideoLayout

    private var libVLC: LibVLC? = null
    private var mediaPlayer: MediaPlayer? = null

    private var videoUrl = ""

    companion object {
        private const val USE_TEXTURE_VIEW = false
        private const val ENABLE_SUBTITLES = true
        private const val VIDEO_URL_PARAM = "video_url_param"

        fun start(context: Context, videoUrl: String) {
            val intent = Intent(context, VideoActivity::class.java)
            intent.putExtra(VIDEO_URL_PARAM, videoUrl)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_activity)

        videoLayout = findViewById(R.id.video_layout)

        val args = ArrayList<String>()
        args.add("-vvv")
        libVLC = LibVLC(this, args)
        mediaPlayer = MediaPlayer(libVLC)

        videoUrl = intent?.extras?.getString(VIDEO_URL_PARAM) ?: ""
    }

    override fun onStart() {
        super.onStart()
        mediaPlayer?.attachViews(
            videoLayout,
            null,
            ENABLE_SUBTITLES,
            USE_TEXTURE_VIEW
        )

        // Load url
        val uri = Uri.parse(videoUrl)
        val media = Media(libVLC, uri)
        mediaPlayer?.media = media
        media.release()

        mediaPlayer?.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        libVLC?.release()
    }


    override fun onStop() {
        super.onStop()
        mediaPlayer?.stop()
        mediaPlayer?.detachViews()
    }
}