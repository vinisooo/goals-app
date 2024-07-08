package com.example.goals.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.goals.R
import com.example.goals.databinding.FragmentAddGoalBinding
import com.example.goals.viewmodels.AddGoalViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddGoalFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAddGoalBinding
    private lateinit var addGoalViewModel: AddGoalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = requireActivity()
        addGoalViewModel = ViewModelProvider(activity).get(AddGoalViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddGoalBinding.inflate(inflater, container, false)
        return binding.root
    }

}