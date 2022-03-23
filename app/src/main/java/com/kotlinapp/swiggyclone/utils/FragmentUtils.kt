package com.kotlinapp.swiggyclone.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class FragmentUtils {
/* private final static String TAG = "FragmentUtils";
    public static void replaceFragmentBackStack(FragmentManager fragmentManager,int container,Fragment fragment,String tag, boolean isAddToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(container, fragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commit();
    }


    public static void addFragmentBackStack(FragmentManager fragmentManager, int containerID,Fragment fragment,String tag, boolean isAddToBackStack){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerID, fragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }*/

    private var TAG:String = "FragmentUtils"
    public fun replaceFragmentBackStack(fragmentManager: FragmentManager, container:Int, fragment:Fragment,tag:String, isAddtoBackSTack:Boolean){
        var fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(container,fragment)
        if(isAddtoBackSTack){

            fragmentTransaction.addToBackStack(tag)
        }
        fragmentTransaction.commit()

    }
    public fun addFragmentBackStack(fragmentManager: FragmentManager, container:Int, fragment:Fragment,tag:String, isAddtoBackSTack: Boolean){

        var fragmentTransaction :FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(container, fragment);
        if (isAddtoBackSTack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commit();

    }

}