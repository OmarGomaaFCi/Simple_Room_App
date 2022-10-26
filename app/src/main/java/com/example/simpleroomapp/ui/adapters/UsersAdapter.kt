package com.example.simpleroomapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleroomapp.R
import com.example.simpleroomapp.pojo.UserModel
import com.example.simpleroomapp.ui.fragments.ListFragmentDirections

class UsersAdapter(
    private val listOfUsers: MutableList<UserModel>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    inner class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val userId = view.findViewById<TextView>(R.id.userId)
        private val firstName = view.findViewById<TextView>(R.id.firstName)
        private val lastName = view.findViewById<TextView>(R.id.lastName)
        private val age = view.findViewById<TextView>(R.id.age)


        fun bind(userModel: UserModel) {
            userId.text = userModel.id.toString()
            firstName.text = userModel.firstName
            lastName.text = userModel.lastName
            age.text = userModel.age.toString()
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listOfUsers[position])

        holder.itemView.setOnClickListener {
            listener.onItemClick(listOfUsers[position])
        }
    }

    override fun getItemCount(): Int {
        return listOfUsers.size
    }

    fun addNote(userModel: UserModel) {
        listOfUsers.add(userModel)
        notifyDataSetChanged()
    }
}


interface OnItemClickListener {
    fun onItemClick(userModel: UserModel)
}