package com.example.songbookapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song(
    val title: String? = null,
    val contentText: String? = null,
    val bpm: Int? = null
): Parcelable