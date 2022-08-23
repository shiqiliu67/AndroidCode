package com.example.videothumbnaildemoapplication

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.videothumbnaildemoapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    val url ="https://cdn-fabric-content-01.touchcast.com/su/1/bd6f3987108de4659b0685fd822fe7b1b3ffbbcb/file_id:7b9a37e22955414cb1dd0f1bf7bbcdfd/sg-eyJsaW5rcyI6eyJmaWxlX2V4dGVybmFsX2xpbmtzIjoiYmN4NzIweHowMSJ9fQ==/touchcast-main-AeKlXmz7/files/7a71fa6b-f0b8-4216-ac86-0356bddca9c0/meta/video.mp4?group=touchcast-main-AeKlXmz7"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.imageVideo
        try {
//            Glide
//                .with(this)
//                .asBitmap()
//                .load(url)//Uri.fromFile(File(url))
//                .into(binding.imageVideo)

          // val thumb: Long = binding.imageVideo.getLayoutPosition() * 1000
            //val options = RequestOptions().frame(thumb)
//            Glide.with(baseContext).load(url).into(binding.imageVideo)
//            Log.e(TAG, "onCreate: url is used inlide", )
            //video view
//            binding.vvVideo.setVideoURI(Uri.parse(url))
           // binding.vvVideo.start()
            binding.vvVideo.let {
                it.setVideoURI(Uri.parse(url))
                if (!it.isPlaying){
                    it.start()
                }
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
}