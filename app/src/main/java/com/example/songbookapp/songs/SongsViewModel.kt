package com.example.songbookapp.songs

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.songbookapp.data.Song
import com.example.songbookapp.repository.FirebaseRepository

class SongsViewModel: ViewModel() {
    private val _firebaseRepository = FirebaseRepository()
    val songs = _firebaseRepository.getSongsData()

}