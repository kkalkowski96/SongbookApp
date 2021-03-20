package com.example.songbookapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.songbookapp.R
import com.example.songbookapp.data.Song

class SongAdapter(private val songListener: OnSongItemClickListener):
    RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private val songsList = ArrayList<Song>()

    fun setSongs(list: List<Song>) {
        songsList.clear()
        songsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class SongViewHolder(view: View): RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener{
                songListener.onSongItemClick(songsList[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_song, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        bindData(holder)
    }

    private fun bindData(holder: SongViewHolder) {
        val songTitle = holder.itemView.findViewById<TextView>(R.id.item_song_title)

        songTitle.text = songsList[holder.adapterPosition].title
    }

    override fun getItemCount(): Int {
        return songsList.size
    }
}

interface OnSongItemClickListener{
    fun onSongItemClick(song: Song, position: Int)
}