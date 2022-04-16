package com.kotlinapp.swiggyclone.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.lang.NullPointerException

class AppSession(context: Context)  {



    var appPreferences:SharedPreferences = context.getSharedPreferences(Constant().PREFERENCES_NAME,Context.MODE_PRIVATE)
    var appSession:AppSession?=null
    var editor:SharedPreferences.Editor = appPreferences.edit()

     fun getInstance(context: Context):AppSession{

         return AppSession(context).also { appSession = it }

     }
   /*  init {
         appPreferences = context.getSharedPreferences(Constant().PREFERENCES_NAME,Context.MODE_PRIVATE)
         editor=appPreferences.edit()

     }*/
     fun clearAll(){

         appPreferences.edit().clear().commit()
       Log.i("CLEAR_ALL","CALLED")
     }
     fun setValue(key:String?,value:String?,context: Context){


         if(appPreferences!=null){
             appPreferences.edit().putString(key,value).apply()

         }else{
             appPreferences = context.getSharedPreferences(Constant().PREFERENCES_NAME,Context.MODE_PRIVATE)
             appPreferences.edit().putString(key,value).apply()


         }

//         editor.putString(key,value)


         //This apply is nesscary when saving something into shared preferences.
//         editor.commit()

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