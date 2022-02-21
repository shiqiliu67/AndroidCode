package com.example.thought_leadership.ui.setting.feedback

import android.app.Dialog
import android.content.Context
import android.content.IntentFilter
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.adobe.marketing.mobile.MobileCore
import com.adobe.marketing.mobile.MobileIdentities
import com.example.thought_leadership.R
import com.example.thought_leadership.data.remote.AccApiDataProvider
import com.example.thought_leadership.data.repository.AccDataRepository
import com.example.thought_leadership.data.response.DiscoverItem
import com.example.thought_leadership.data.response.PfMedia
import com.example.thought_leadership.data.room_db.SavedContent
import com.example.thought_leadership.data.room_db.UsersDataBase
import com.example.thought_leadership.databinding.ActivityFeedbackBinding
import com.example.thought_leadership.ui.mainloading.MainActivity
import com.example.thought_leadership.ui.mainloading.TheBottomSheetDialogMainActivity
import com.example.thought_leadership.ui.specific_article.ContactUsViewModel
import com.example.thought_leadership.util.Captcha
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_feedback.view.*
import javax.inject.Inject
import com.google.android.material.chip.Chip
import com.mparticle.MParticle
import com.mparticle.MParticleOptions
import com.mparticle.kits.AdobeApi
//import com.mparticle.MParticle
//import com.mparticle.MParticleOptions
//import com.mparticle.kits.AdobeApi
//import com.mparticle.MParticle
//import com.mparticle.kits.AdobeApi
import kotlinx.coroutines.*
import java.sql.Timestamp
import java.util.*
import kotlin.collections.HashMap
import com.adobe.marketing.mobile.AdobeCallback
import com.adobe.marketing.mobile.Identity


class FeedbackActivity  : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

//    @Inject
//    lateinit var repository : AccDataRepository

    lateinit var binding :ActivityFeedbackBinding
    lateinit var bitmap: Bitmap
    lateinit var codeNumber: String


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(FeedbackViewModel::class.java)
    }


    var checkedText = ""

    var chipGroupChecked = false
    lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    lateinit var bottomSheetDialog: TheBottomSheetDialogMainActivity

    var activeBtn :Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_feedback)

        binding.feedbackViewModel = viewModel

        val window = window
        val decorView = window.decorView
        val wic = WindowInsetsControllerCompat(window, decorView)

//
//        var options = MParticleOptions.builder(this)
//            .credentials("REPLACE WITH APP KEY", "REPLACE WITH APP SECRET")
//            .environment(MParticle.Environment.Development)
//            .dataplan("mobile_data_plan", 2)
//            .build()

//        MobileCore.getSdkIdentities()
        MobileCore.getSdkIdentities() {
            Log.d("AdobeanAlytics32","Log : ${it}")
            // handle the json string
        }



        Identity.getExperienceCloudId(AdobeCallback<String?> {
            //Handle the ID returned here
            Log.d("AdobeanAlytics32","Log2 : ${it}")
        })


        var options = MParticleOptions.builder(this)
            .environment(MParticle.Environment.Development)
            .build()

        MParticle.start(options)
//
        val particle = MParticle.getInstance()
//        mKitManager
//
//        Context.registerReceiver(particle,  IntentFilter(MParticle.ServiceProviders.BROADCAST_ACTIVE + MParticle.ServiceProviders.APPBOY));
//
//        if (particle!!.isKitActive(MParticle.ServiceProviders.ADOBE)) {
//
//
//        }
//
//
//
        particle?.let{
            val adobe= it.getKitInstance(MParticle.ServiceProviders.ADOBE)

            if (adobe != null) {
            //access the MID
                val mid: String = (adobe as AdobeApi).getMarketingCloudID()
                Log.d("AdobeanAlytics32",mid.toString())
            }
            else
            {
                Log.d("AdobeanAlytics32","NULL")
            }
        }
//        val adobe: AdobeApi =
//            MParticle.getInstance()!!.getKitInstance(MParticle.ServiceProviders.ADOBE) as AdobeApi
//        if (adobe != null) {
//            //access the MID
//            val mid: String = adobe.getMarketingCloudID()
//        }

   //     MobileCore.getSdkIdentities()

 //       MobileIdentities.
//
//        ACPIdentity.getExperienceCloudId { mid in
//                AnalyticsManager.mid = mid ?? ""
//        }
//
//        ACPCore.trackAction(“event.SubMenuClick “, data:info)



        val info: HashMap<String, String> = HashMap<String, String>()
        info.put("formName", "FeedbackActivity")
        info.put("mid","TemporaryMid")
        info.put("locale",Locale.getDefault().toLanguageTag())
        info.put("timeStamp", Timestamp(Date().getTime()).toString())
        MobileCore.trackAction("event.FormStart", info)


        wic.isAppearanceLightStatusBars = true // true or false as desired.
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.white))

        initToolbar()
        initBtn()
        setUpPlayEvents()
        initBottomSheet()
        createCode()//create captcha
        setUpEvent()
    }


    private fun setUpEvent() {
        var etFeedback = false
        var etCaptcha = false
        var enterText : String? = null


        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            chipGroupChecked = when {
                (checkedId < 0) -> false
                else -> true
            }


            val chip: Chip? = binding.chipGroup.findViewById(checkedId)
            if(chip!=null){
                checkedText = chip.text.toString() //binding.chipGroup.get(checkedId).transitionName

            }
            else{
                checkedText = ""
            }

          //  checkedText = binding.chipGroup.findViewById<View>(checkedId).textAlignment

            buttonSetEnable(
                chipGroupChecked && (binding.editTextFeedback.getText().toString().length > 0)
            )
        }


        binding.editTextFeedback.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
               etFeedback = binding.editTextFeedback.text.isNotEmpty()
                buttonSetEnable(
                    chipGroupChecked && (binding.editTextFeedback.getText().toString().length > 0)
                )

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        binding.etCaptcha.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                etCaptcha = binding.etCaptcha.text.isNotEmpty()
                Log.e(TAG, "setUpEvent: activeBtn :$activeBtn ")
                 enterText = binding.etCaptcha.text.toString()
                Log.e(TAG, "createCode: enterText $enterText, code is $codeNumber ")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })



        buttonSetEnable(activeBtn)



        binding.buttonSend.setOnClickListener {

            val info: HashMap<String, String> = HashMap<String, String>()
            info.put("formName", "FeedbackActivity")
            info.put("mid","TemporaryMid")
            info.put("locale",Locale.getDefault().toLanguageTag())
            info.put("timeStamp",Timestamp(Date().getTime()).toString())



            if (enterText.equals(codeNumber, ignoreCase = false)) {

                MobileCore.trackAction("event.FormSuccess", info)

//                val info: HashMap<String, String> = HashMap<String, String>()
//                info.put("formName", form name)
//                info.put("mid","TemporaryMid")
//                info.put("timeStamp",MainActivity.getTimeStamp())
//                MobileCore.trackAction("event.FormStart", info)



                viewModel.sendFeedback(checkedText)
        //        viewlifecycleScope  lifecycleScope
//                GlobalScope.launch(Dispatchers.IO){
//
//                    val dao = UsersDataBase.getInstance(context = baseContext).userDao
//
//                    val user = dao.getCurrentUser(UsersDataBase.provisionalUserName)
//                //    withContext(Dispatchers.Main) {
//                    val response = repository.postFeedbackData(
//                            email=user.email,
//                            name = user.username,
//                            category = checkedText,
//                            feedback = binding.editTextFeedback.text.toString())
//                        Log.d("FeedbackActivity32","response = ${response.isSuccessful}")
//                        Log.d("FeedbackActivity32","response = ${response.body()}")
//              //      }
//
//
////                    val response = repository.postFeedbackData(
////                            email="test@test.com",
////                        name = "USER_NAME",
////                        category = checkedText,
////                        feedback = binding.editTextFeedback.text.toString())
//
//
//                }

                showDialog(true)




            } else {
                //show dialog to show

                MobileCore.trackAction("event.FormError", info)

                showDialog(false)
            }


        }
    }









    private fun initBottomSheet() {




        //init bottomsheet with its peek height
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetCoordinatorLayout.bottomSheetMainLoading)
        bottomSheetBehavior.peekHeight = 200
        //set bottom sheet init state is hidden
        bottomSheetBehavior.isHideable = true
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        // bottom_sheet_collapse.visibility = View.GONE
        //if bottom sheet behavior state == collapsed ->change layout
        // idea-> just make collapsed and expends in one layout when collapsed -> collapsed layout.visibility = visible, else-> visisblity = gone
        //checkState()
        //call back

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        binding.bottomSheetCoordinatorLayout.layoutCollapseMainLoading.visibility = View.VISIBLE

                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        //       binding.layoutCollapseMainLoading.visibility = View.GONE
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        //Toast.makeText(baseContext,"hidden",Toast.LENGTH_SHORT).show()
                        try {
                            MainActivity.mediaPlayer?.pause()
                            MainActivity.mediaTracker?.trackPause()
                            binding.bottomSheetCoordinatorLayout.layoutCollapseMainLoading.visibility = View.GONE
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        binding.bottomSheetCoordinatorLayout.layoutCollapseMainLoading.visibility = View.GONE
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

        })




        binding.bottomSheetCoordinatorLayout.layoutCollapseMainLoading.setOnClickListener {


            if(!::bottomSheetDialog.isInitialized){
                bottomSheetDialog = TheBottomSheetDialogMainActivity()
            }

            if(!bottomSheetDialog.isAdded()){
                bottomSheetDialog.show(supportFragmentManager,"TheBottomSheetDialog")
            }
            else{
                bottomSheetDialog.dismiss()
            }



            //   showBottomSheetDialog(DEFAULT_MEDIA_URL,mediaPlayer!!,mediaPlaying!!)
        }
    }





    private fun setMiniDrawer(){
        var savedItem: SavedContent?=null
        if(MainActivity.mediaPlayer!=null ){ // && MainActivity.mediaPlayer!!.isPlaying
            if (MainActivity.mediaPlaying is PfMedia){
                savedItem = SavedContent(
                    theUrl = (MainActivity.mediaPlaying as PfMedia).url,
                    userID = UsersDataBase.provisionalUserName,
                    title = (MainActivity.mediaPlaying as PfMedia).title,
                    content_type =(MainActivity.mediaPlaying as PfMedia).content_type,
                    published_date = (MainActivity.mediaPlaying as PfMedia).published_date,
                    summary = (MainActivity.mediaPlaying as PfMedia).summary,
                    thumbnail_url = (MainActivity.mediaPlaying as PfMedia).thumbnail_url,
                    appFeed = "personal"
                )
                Picasso.get().load(savedItem.thumbnail_url).into(binding.bottomSheetCoordinatorLayout.headerImage)
                binding.bottomSheetCoordinatorLayout.tvTitle.text = savedItem.title
                binding.bottomSheetCoordinatorLayout.tvAuthor.text = savedItem.content_type

            }
            else if(MainActivity.mediaPlaying is DiscoverItem)
            {
                val arrIndexes = (MainActivity.mediaPlaying as DiscoverItem).description.indexesOf("\$URL\$")
                var theMainUrl:String
                var imageUrl:String
                if(arrIndexes.size>=2) {

                    theMainUrl = (MainActivity.mediaPlaying as DiscoverItem).description.substring( arrIndexes.get(0)+ 5,arrIndexes.get(1))

                    imageUrl = (MainActivity.mediaPlaying as DiscoverItem).description.substring( arrIndexes.get(1)+ 5)

                }
                else
                {
                    theMainUrl = ""
                    imageUrl = ""
                }
                savedItem = SavedContent(
                    theUrl = theMainUrl,
                    userID = UsersDataBase.provisionalUserName,
                    title = (MainActivity.mediaPlaying as DiscoverItem).name,
                    content_type =(MainActivity.mediaPlaying as DiscoverItem).type?:"content_type",
                    published_date = (MainActivity.mediaPlaying as DiscoverItem).published.toString(),
                    summary = (MainActivity.mediaPlaying as DiscoverItem).description,
                    thumbnail_url = imageUrl,
                    appFeed = "discover"
                )
                Picasso.get().load(savedItem.thumbnail_url).into(binding.bottomSheetCoordinatorLayout.headerImage)
                binding.bottomSheetCoordinatorLayout.tvTitle.text = savedItem.title
                binding.bottomSheetCoordinatorLayout.tvAuthor.text = savedItem.content_type
            }
            else if(MainActivity.mediaPlaying is SavedContent){
                savedItem = MainActivity.mediaPlaying as SavedContent
                Picasso.get().load(savedItem.thumbnail_url).into(binding.bottomSheetCoordinatorLayout.headerImage)
                binding.bottomSheetCoordinatorLayout.tvTitle.text = savedItem.title
                binding.bottomSheetCoordinatorLayout.tvAuthor.text = savedItem.content_type

            }
            bottomSheetBehavior.state = MainActivity.lastBottomSheetBehaviorState
            if(bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED){
                binding.bottomSheetCoordinatorLayout.layoutCollapseMainLoading.visibility = View.VISIBLE
            }
            if(MainActivity.mediaPlaying!=null && savedItem!=null){
                if (!::bottomSheetDialog.isInitialized) {
                    bottomSheetDialog = TheBottomSheetDialogMainActivity()

                    bottomSheetDialog.setTheMainMusicUrl(savedItem,this)
                }

                if(MainActivity.mediaPlayer!!.isPlaying){
                    binding.bottomSheetCoordinatorLayout.ivPlayCollapse.setImageResource(R.drawable.ic_icon_pause_white_with_circle) //ic_baseline_play_arrow_24

                }
                else
                {
                    binding.bottomSheetCoordinatorLayout.ivPlayCollapse.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        MainActivity.lastBottomSheetBehaviorState = bottomSheetBehavior.state
        MobileCore.lifecyclePause()

    }

    override fun onResume() {
        super.onResume()
        MobileCore.setApplication(application)
        MobileCore.lifecycleStart(null)
        setMiniDrawer()

    }

    fun String?.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> {
        tailrec fun String.collectIndexesOf(offset: Int = 0, indexes: List<Int> = emptyList()): List<Int> =
            when (val index = indexOf(substr, offset, ignoreCase)) {
                -1 -> indexes
                else -> collectIndexesOf(index + substr.length, indexes + index)
            }

        return when (this) {
            null -> emptyList()
            else -> collectIndexesOf()
        }
    }


    private fun setUpPlayEvents(){
        binding.bottomSheetCoordinatorLayout.ivPlayCollapse.setOnClickListener {
            if (MainActivity.mediaPlayer == null) {
            }
            if (MainActivity.mediaPlayer?.isPlaying == true) {
                binding.bottomSheetCoordinatorLayout.ivPlayCollapse.setImageResource(R.drawable.ic_baseline_play_arrow_24) //ic_baseline_play_arrow_24
                MainActivity.mediaPlayer?.pause()
                MainActivity.mediaTracker?.trackPause()
                //   Toast.makeText(baseContext, "MediaPlayer paused", Toast.LENGTH_SHORT).show()
            } else {
                binding.bottomSheetCoordinatorLayout.ivPlayCollapse.setImageResource(R.drawable.ic_icon_pause_white_with_circle)
                MainActivity.mediaPlayer?.start()
                MainActivity.mediaTracker?.trackPlay()
                // Toast.makeText(baseContext, "MediaPlayer start", Toast.LENGTH_SHORT).show()
            }
        }

    }














    private fun showDialog( success:Boolean){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_captcha)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        var title = dialog.findViewById<TextView>(R.id.tv_title)
        var description = dialog.findViewById<TextView>(R.id.tv_incorrect_captcha)


        if(success){
            title.text = "Success"
            description.text = "Feedback submitted successfully"
        }
        else
        {
            title.text = "Error"
            description.text = "Captcha doesn't match!"

        }


        dialog.show()
        val btn = dialog.findViewById<TextView>(R.id.btn_ok)
        btn.setOnClickListener {
            dialog.dismiss()
            if(success){
                onBackPressed()
            }


        }
    }

    private fun createCode() {

        bitmap = Captcha.instance!!.createBitmap()
        binding.ivShowCode.setImageBitmap(bitmap)
        codeNumber = Captcha.instance!!.code!! //captcha string
        binding.ivShowCode.setOnClickListener {
            bitmap = Captcha.instance!!.createBitmap()
            binding.ivShowCode.setImageBitmap(bitmap)
            codeNumber = Captcha.instance!!.code!! //captcha string
        }


    }
    private fun buttonSetEnable(status: Boolean) {
        binding.buttonSend.isEnabled = status
    }
    private fun initToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar_return) as Toolbar
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar!!.title = ""
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    private fun initBtn(){
        val states = arrayOf(
            intArrayOf(android.R.attr.state_enabled), // enabled
            intArrayOf(-android.R.attr.state_enabled) // disabled
        )
        val colors = intArrayOf(
            Color.parseColor("#A100FF"), // enabled color  #A100FF
            Color.parseColor("#DCAFFF") // disabled color
        )
        val colorStates = ColorStateList(states, colors)

        binding.buttonSend.backgroundTintList = colorStates
    }
    //handle back pressed button to previous
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
    companion object{
        private val TAG = "FeedbackActivity"
    }
}