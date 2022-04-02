package com.kotlinapp.swiggyclone.sharedPreferences

import android.content.Context
import android.content.SharedPreferences

 class AppSession(context: Context)  {


    var appPreferences:SharedPreferences?=null
    var appSession:AppSession?=null

    init {
      if(appPreferences==null){
          appSession = AppSession(context)

      }
        appPreferences  =context.getSharedPreferences(Constant().PREFERENCES_NAME,Context.MODE_PRIVATE)

    }
    fun getInstance(context: Context):AppSession{
        if(appPreferences==null){
              appSession = AppSession(context)

        }

        return appSession!!

    }
     fun clearAll(){

         appPreferences!!.edit().clear().commit()
     }
     fun setValue(key:String?,value:String?){
         var editor:SharedPreferences.Editor=appPreferences!!.edit()
         editor.putString(key,value)

     }

     fun getValue(key: String?): String? {
         return if (appPreferences != null) {
             appSession!!.getString(key)
         } else ""
     }
     fun getString(key: String?): String? {
         return appPreferences!!.getString(key, "")
     }


 }