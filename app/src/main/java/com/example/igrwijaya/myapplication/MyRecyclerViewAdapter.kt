package com.example.igrwijaya.myapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.Collections

import android.graphics.Movie
import com.example.igrwijaya.myapplication.user.adapter.UserList


/**
 * Created by igrwijaya on 6/4/2017.
 */

class MyRecyclerViewAdapter : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {


    private var usersList: List<UserList> = null!!

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView
        var avatar: TextView

        init {
            name = view.findViewById(R.id.userName) as TextView
            avatar = view.findViewById(R.id.userAvatar) as TextView
        }
    }


    fun MyRecyclerViewAdapter(myUserList: List<UserList>) {
        this.usersList = myUserList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.data_list_design, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = usersList[position]
        holder.name.setText(user.getName())
        holder.avatar.setText(user.getPic())
    }

    override fun getItemCount(): Int {
        return usersList.size
    }
}
