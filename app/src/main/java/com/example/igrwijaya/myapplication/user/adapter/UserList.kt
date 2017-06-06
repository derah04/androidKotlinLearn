package com.example.igrwijaya.myapplication.user.adapter

/**
 * Created by igrwijaya on 6/6/2017.
 */
data class inputUser(var name: String, var pic: String)

class UserList{

    fun userList(name: String, pic: String){
        val data = inputUser(name,pic)
    }
}