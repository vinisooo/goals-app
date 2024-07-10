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

    private var itemList: MutableList<Check> = mutableListOf()

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
        setListeners()
        return binding.root
    }

    private fun renderCheckChips() {
        val adapter = CheckChipAdapter(requireContext(), itemList)
        adapter.addToCheckChips(binding.checkChipGroup)
    }

    private fun setListeners() {
        binding.addCheck.setOnEditorActionListener{_, actionId, _ ->
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_DONE) {
                addCheck()
                true
            } else {
                false
            }
        }
    }

    private fun addCheck () {
        val newCheckName = binding.addCheck.text.toString()

        val check = Check(id = 0, name=newCheckName, checked=false)
        itemList.add(check)

        binding.addCheck.text = ""
        renderCheckChips()
    }
}