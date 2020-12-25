package com.example.androidboilerplate.ui.sample

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.androidboilerplate.repositories.SampleRepository

class SampleViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    sampleRepository: SampleRepository,
) : ViewModel()