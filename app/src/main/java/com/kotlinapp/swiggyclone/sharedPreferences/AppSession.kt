package com.kotlinapp.swiggyclone.sharedPreferences

import android.content.Context
import android.content.SharedPreferences

 class AppSession(context: Context)  {


    var appPreferences:SharedPreferences = context.getSharedPreferences(Constant().PREFERENCES_NAME,Context.MODE_PRIVATE)
    var appSession:AppSession?=null
     private lateinit var editor:SharedPreferences.Editor

     init {
         appPreferences = context.getSharedPreferences(Constant().PREFERENCES_NAME,Context.MODE_PRIVATE)
         editor=appPreferences.edit()

     }
     fun clearAll(){

         appPreferences.edit().clear().commit()
     }
     fun setValue(key:String?,value:String?,context: Context){
         editor.putString(key,value)

         //This apply is nesscary when saving something into shared preferences.
         editor.apply()

     }
     fun getValue(key: String?,context: Context): String? {

         if(appPreferences!=null){
             return appPreferences.getString(key,"")
         }else{
             return ""
         }
     }
     fun getString(key: String?): String? {
         return appPreferences.getString(key, "")
     }


 }