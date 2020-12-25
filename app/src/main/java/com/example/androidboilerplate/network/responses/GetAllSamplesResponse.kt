package com.example.androidboilerplate.network.responses

import com.example.androidboilerplate.database.entities.Sample

data class GetAllSamplesResponse(
    val samples: List<Sample>
)