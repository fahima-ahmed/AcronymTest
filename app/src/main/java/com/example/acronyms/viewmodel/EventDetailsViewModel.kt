package com.fahima.assessment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.acronyms.model.AcronymResult

class EventDetailsViewModel: ViewModel() {

    val eventDetailData: MutableLiveData<AcronymResult> = MutableLiveData()

    fun setEventData(event: AcronymResult) {
        eventDetailData.value = event
    }
}