package com.example.navdemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var firstFrg : FirstFragment
    lateinit var secondFrg : SecondFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // setUpView()
        setNavController()
    }
    private fun setNavController(){
        val navController = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
        bottomNavigationView.setupWithNavController(navController)
    }
    private fun setUpView(){
        firstFrg = FirstFragment()
        secondFrg = SecondFragment()
        //bottom nav bar
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        bottomNavigationView.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.firFrg -> setCurrentFragment(firstFrg)
//                R.id.secondFrg -> setCurrentFragment(secondFrg)
//            }
//            true
//        }
       // bottomNavigationView.selectedItemId = R.id.firFrg
        //nav graph
        val navController: NavController = Navigation.findNavController(this, R.id.fragmentContainerView)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
            commit()
        }

    @SuppressLint("RestrictedApi")
    private fun customSetNavController() {//delete the nav_graph
        val NAVIGATOR_TO_VIEW = "editor"
        val startDestinationId = if (NAVIGATOR_TO_VIEW.isNotEmpty() && NAVIGATOR_TO_VIEW.isNotBlank()) {
            when (NAVIGATOR_TO_VIEW) {
                "media" -> R.id.media_nav
                "myfeed" -> R.id.myfeed_nav
                "topic" -> R.id.topic_nav
                else -> R.id.editor_nav
            }
        } else {
            R.id.editor_nav
        }


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navGraphIds = listOf(R.navigation.editor_nav, R.navigation.myfeed_nav, R.navigation.media_nav, R.navigation.topic_nav)
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            startDestinationId = startDestinationId,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fragmentContainerView,
            intent = intent
        )
        controller.observe(this) { navController ->
            navController.currentDestination?.let {
              //  currentSelectedTab = viewModel.getTabName(it.label.toString())
            }
//            if (::bottomSheetDialog.isInitialized && bottomSheetDialog.bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
//                bottomSheetDialog.dismiss()
//            }
        }
     //   currentNavController = controller

    }

}