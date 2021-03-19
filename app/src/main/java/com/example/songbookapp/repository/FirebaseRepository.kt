package com.example.songbookapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.songbookapp.data.Song
import com.example.songbookapp.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FirebaseRepository {
    private val FIREBASE_REPOSITORY_TAG = "FirebaseRepository"
    private val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getUserInformationData(): LiveData<User> {
        val usersCloudResult = MutableLiveData<User>()
        val uid: String? = firebaseAuth.currentUser?.uid

        firebaseFirestore
            .collection("users")
            .document(uid!!)
            .get()
            .addOnSuccessListener {
                val user: User? = it.toObject(User::class.java)
                usersCloudResult.postValue(user)
            }
            .addOnFailureListener {
                Log.d(FIREBASE_REPOSITORY_TAG, it.message.toString())
            }
        return usersCloudResult
    }

    fun getSongsData(): LiveData<List<Song>> {
        val songsCloudResult = MutableLiveData<List<Song>>()

        firebaseFirestore
            .collection("songs")
            .get()
            .addOnSuccessListener {
                val songs: MutableList<Song> = it.toObjects(Song::class.java)
                songsCloudResult.postValue(songs)
            }
            .addOnFailureListener {
                Log.d(FIREBASE_REPOSITORY_TAG, it.message.toString())
            }
        return songsCloudResult
    }

}