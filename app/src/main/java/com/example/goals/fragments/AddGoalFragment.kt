package com.example.goals.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.node.getOrAddAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.goals.R
import com.example.goals.adapters.CheckChipAdapter
import com.example.goals.databinding.FragmentAddGoalBinding
import com.example.goals.models.Check
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
        renderCheckChips()
        return binding.root
    }

    private fun renderCheckChips () {
        val itemlist: List<Check> = listOf(
            Check(id = 1, checked = true, name = "teste"),
            Check(id = 2, checked = true, name = "teste")
        )

        val adapter = CheckChipAdapter(requireContext(), itemlist)
        adapter.addToCheckChips(binding.checkChipGroup)
    }
}