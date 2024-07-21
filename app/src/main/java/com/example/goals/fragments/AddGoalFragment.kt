package com.example.goals.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.goals.R
import com.example.goals.adapters.CheckChipAdapter
import com.example.goals.databinding.FragmentAddGoalBinding
import com.example.goals.models.Check
import com.example.goals.viewmodels.AddGoalViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddGoalFragment : BottomSheetDialogFragment(), CheckChipAdapter.CheckChipCallback {
    private lateinit var binding: FragmentAddGoalBinding
    private lateinit var addGoalViewModel: AddGoalViewModel

    private var itemList: MutableList<Check> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = requireActivity()
        addGoalViewModel = ViewModelProvider(activity).get(AddGoalViewModel::class.java)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
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
        val adapter = CheckChipAdapter(requireContext(), itemList, this)
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

        val check = Check(id = 0, name=newCheckName, checked=false, goalId=0)
        itemList.add(check)

        binding.addCheck.setText("")
        renderCheckChips()
    }

    override fun onRemoveCheck () {
        this.renderCheckChips()
    }
}