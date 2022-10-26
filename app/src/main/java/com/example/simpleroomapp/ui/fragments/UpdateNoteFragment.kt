package com.example.simpleroomapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.simpleroomapp.R
import com.example.simpleroomapp.databinding.FragmentUpdateNoteBinding
import com.example.simpleroomapp.pojo.UserModel
import com.example.simpleroomapp.ui.viewmodels.UserViewModel

class UpdateNoteFragment : Fragment() {

    private val args by navArgs<UpdateNoteFragmentArgs>()
    private lateinit var binding: FragmentUpdateNoteBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!)
        )[UserViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.etFirstNameUpdate.setText(args.user.firstName)
        binding.etLastNameUpdate.setText(args.user.lastName)
        binding.etAgeUpdate.setText(args.user.age.toString())

        binding.btnUpdateNote.setOnClickListener {
            userViewModel.updateUser(
                UserModel(
                    args.user.id,
                    binding.etFirstNameUpdate.text.toString() ,
                    binding.etLastNameUpdate.text.toString() ,
                    binding.etAgeUpdate.text.toString().toInt()
                )
            )
            val action = UpdateNoteFragmentDirections.actionUpdateNoteFragmentToListFragment()
            findNavController().navigate(action)
        }

        binding.deleteNote.setOnClickListener {
            userViewModel.deleteUser(args.user)
            val action = UpdateNoteFragmentDirections.actionUpdateNoteFragmentToListFragment()
            findNavController().navigate(action)
        }
    }

}