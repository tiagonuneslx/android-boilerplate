package com.example.androidboilerplate.ui.sample

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.androidboilerplate.database.entities.Sample
import com.example.androidboilerplate.repositories.SampleRepository
import kotlinx.coroutines.flow.onStart

class SampleViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    sampleRepository: SampleRepository,
) : ViewModel() {

    val latestSamplesWithAbbrTitle: LiveData<List<Sample>> =
        sampleRepository.getLatestSamplesAbbrTitle(20)
            .onStart { emit(listOf(Sample(12903, "YO", ""))) }
            .asLiveData(viewModelScope.coroutineContext)
}