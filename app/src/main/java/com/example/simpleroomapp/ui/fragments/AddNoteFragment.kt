package com.example.simpleroomapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.simpleroomapp.R
import com.example.simpleroomapp.databinding.FragmentAddNoteBinding
import com.example.simpleroomapp.databinding.FragmentListBinding
import com.example.simpleroomapp.pojo.UserModel
import com.example.simpleroomapp.ui.viewmodels.UserViewModel

class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!)
        )[UserViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddNote.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val age = binding.etAge.text.toString()

            if (firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()) {
                userViewModel.insertUser(UserModel(0, firstName, lastName, age.toInt()))
                val action = AddNoteFragmentDirections.actionAddNoteFragmentToListFragment()
                findNavController().navigate(action)
            }
            else {
                Toast.makeText(context , "Please fill all spaces !!",Toast.LENGTH_SHORT).show()
            }
        }
    }


}