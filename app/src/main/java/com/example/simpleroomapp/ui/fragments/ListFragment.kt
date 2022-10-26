package com.example.simpleroomapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleroomapp.databinding.FragmentListBinding
import com.example.simpleroomapp.pojo.UserModel
import com.example.simpleroomapp.ui.adapters.OnItemClickListener
import com.example.simpleroomapp.ui.adapters.UsersAdapter
import com.example.simpleroomapp.ui.viewmodels.UserViewModel


class ListFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: FragmentListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!)
        )[UserViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToAddNoteFragment()
            findNavController().navigate(action)
        }

        userViewModel.allUsersData.observe(viewLifecycleOwner , Observer { users ->
            val usersAdapter = UsersAdapter(users.toMutableList() , object : OnItemClickListener{
                override fun onItemClick(userModel: UserModel) {
                    val action = ListFragmentDirections.actionListFragmentToUpdateNoteFragment(userModel)
                    findNavController().navigate(action)
                }
            })
            binding.recyclerView.apply {
                adapter = usersAdapter
                layoutManager = LinearLayoutManager(context)
            }
        })
    }

}