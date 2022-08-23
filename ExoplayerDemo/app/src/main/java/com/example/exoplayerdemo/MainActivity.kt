package com.example.exoplayerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exoplayerdemo.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
import com.google.android.exoplayer2.util.Util

class MainActivity : AppCompatActivity() {
    var playWhenReady : Boolean = false
    var playbackPosition =0
    var currentWindow =0
    var mPlayer : ExoPlayer? = null
    var url = "https://acnmedia.accenture.com/images/thought-leadership-app/images/Maturity+Index+-+A.com+Marquee+-+mobile+-+1080x1920+-+V4+Loop.mp4"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }
    private fun initView(){
        try {
            mPlayer = SimpleExoPlayer.Builder(this)
                .build()
            MediaCodecRenderer()
            binding.exoplayerView.let {
                it.player =mPlayer
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
    private fun releasePlayer(){
        if (mPlayer!=null){
            playWhenReady = mPlayer!!.playWhenReady
            playbackPosition = mPlayer!!.currentPosition.toInt()
            currentWindow = mPlayer!!.currentWindowIndex
            mPlayer!!.release()
            mPlayer = null
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT>=24){
            initView()
        }
    }

    override fun onStop() {
        if (Util.SDK_INT>=24){
            releasePlayer()
        }
        super.onStop()

    }
}