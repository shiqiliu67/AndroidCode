package com.example.thought_leadership.ui.setting.feedback

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.thought_leadership.data.repository.AccDataRepository
import com.example.thought_leadership.data.room_db.UsersDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedbackViewModel @Inject constructor(private val application: Application, context: Application): ViewModel() {

    val mcontext = context

    @Inject
    lateinit var repository : AccDataRepository

    var content:String?=null

    fun sendFeedback(checkedText:String){

        val dao = UsersDataBase.getInstance(mcontext).userDao
        GlobalScope.launch(Dispatchers.IO){
            val user = dao.getCurrentUser(UsersDataBase.provisionalUserName)


            //    withContext(Dispatchers.Main) {
            val response = repository.postFeedbackData(
                email=user.email,
                name = user.username,
                category = checkedText,
                feedback = content!!)
            Log.d("FeedbackActivity321","response = ${response.isSuccessful}")
            Log.d("FeedbackActivity321","response = ${response.body()}")


        }
    }


}