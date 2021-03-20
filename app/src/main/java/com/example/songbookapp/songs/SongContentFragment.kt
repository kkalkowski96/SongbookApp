package com.example.songbookapp.songs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.songbookapp.R
import com.example.songbookapp.data.Song
import com.example.songbookapp.databinding.FragmentSongContentBinding

class SongContentFragment : Fragment() {

    private var binding: FragmentSongContentBinding? = null
    private lateinit var song: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            song = it.getParcelable("song")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSongContentBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            songContentFragment = this@SongContentFragment
        }
    }

    fun getSong(): Song {
        return song
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}