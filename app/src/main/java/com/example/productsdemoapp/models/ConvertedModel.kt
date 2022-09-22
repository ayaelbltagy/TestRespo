package com.example.productsdemoapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class ConvertedModel( val id: Long,
                           val title: String,
                           val description: String,
                           val price: Double,
                           val prepare_time: Double): Parcelable
