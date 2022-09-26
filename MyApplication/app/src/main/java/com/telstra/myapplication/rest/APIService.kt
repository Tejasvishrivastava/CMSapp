package com.example.retrofitlibrary.rest

import android.R.attr.defaultValue
import android.R.attr.fragment
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.media.session.MediaSession
import android.os.Bundle
import android.util.Log.e
import androidx.preference.PreferenceManager
import com.example.retrofitlibrary.model.User
import com.example.retrofitlibrary.model.UserResponse
import com.telstra.myapplication.Fragments.SignInFragment
import com.telstra.myapplication.model.*
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.selects.SelectInstance
import retrofit2.Call
import retrofit2.http.*
import java.security.AccessController.getContext

public interface SharedPreferences

/*class pref{
    fun defaultPrefs(context: Context): SharedPreferences?
            = PreferenceManager.getDefaultSharedPreferences(context)

    inline fun SharedPreferences.editor(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }
}*/

    var  mytoken: String = "default"
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

interface APIService {
   // @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
   // fun fetchQuestions(@Query("tagged") tags: String): Call<QuestionList>




    @Headers("Accept: application/json")
    @POST("/signin")
    fun signIn( @Body user: User):Call<UserResponse>

    @Headers("Accept: application/json")
   @POST("/signup")
    fun signUp(@Body register: Register):Call<RegisterResponse>

    @Headers("Accept: application/json")//,"Auth: Owner eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImpvaG5AZG9lLmNvbSIsInVzZXJJZCI6IjYzMTVkMDllMzk1MmUwM2ZlYjM5YzllYyIsImlhdCI6MTY2Mzg0OTQ3NCwiZXhwIjoxNjYzOTM1ODc0fQ.XRpxt5Fd1-juK5PpODwIl9SNUoJfbryZJuHeI5gywRA")
    @POST("/createselfsigned")
    fun createSelfSignedCertificate(@Header ("Auth") token :String,@Body selfSignReq : SelfSignRequest):Call<SelfSignResponse>

    @Headers("Accept: application/json")
    @POST("/createrootsigned")
    fun createSignedCertificate(@Header ("Auth") token :String,@Body signRequest: SignedCertificateRequest):Call<SignedCertificateResponse>

    @Headers("Accept: application/json")
    @POST("/createlca")
    fun createCaCertificate(@Header ("Auth") token :String,@Body caCertRequest: CaCertificateRequest):Call<CaCertificateResponse>
}

//"Auth: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImpvaG5AZG9lLmNvbSIsInVzZXJJZCI6IjYzMTVkMDllMzk1MmUwM2ZlYjM5YzllYyIsImlhdCI6MTY2MzU4MDIzMCwiZXhwIjoxNjYzNjY2NjMwfQ.GLxL7YAgFTKrlX0nCQ9gervagir8lC3-4DEVns1pL-Y