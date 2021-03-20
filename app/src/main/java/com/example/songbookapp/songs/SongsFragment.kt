package com.example.songbookapp.songs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.songbookapp.R
import com.example.songbookapp.adapter.OnSongItemClickListener
import com.example.songbookapp.adapter.SongAdapter
import com.example.songbookapp.data.Song
import com.example.songbookapp.databinding.FragmentSongsBinding


class SongsFragment : Fragment(), OnSongItemClickListener {

    private var binding: FragmentSongsBinding? = null
    private val songsViewModel: SongsViewModel by viewModels()
    private val songsAdapter = SongAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bindingFragment = FragmentSongsBinding.inflate(inflater, container, false)
        binding = bindingFragment
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.fragmentSongsRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.fragmentSongsRecyclerView?.adapter = songsAdapter
        songsViewModel.songs.observe(viewLifecycleOwner, {
            songsAdapter.setSongs(it)
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onSongItemClick(song: Song, position: Int) {
       Toast.makeText(requireContext(), song.bpm.toString(), Toast.LENGTH_LONG).show()
    }
}