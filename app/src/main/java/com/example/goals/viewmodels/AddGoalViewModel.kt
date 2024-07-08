package com.example.goals.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddGoalViewModel: ViewModel() {
    var name: MutableLiveData<String>? = null
    var description: MutableLiveData<String>? = null
    var price: MutableLiveData<String>? = null
}
