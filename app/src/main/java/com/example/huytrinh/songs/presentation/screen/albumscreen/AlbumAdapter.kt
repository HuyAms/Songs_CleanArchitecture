package com.example.huytrinh.songs.presentation.screen.albumscreen

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.huytrinh.songs.R
import com.example.huytrinh.songs.domain.entity.Album

class AlbumAdapter(
        var albums: List<Album>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        this.context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)

    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums[position]
        val vh = holder as AlbumViewHolder

        vh.albumNameTxt.text = album.name
        vh.numberOfSongsTxt.text = "${album.numberOfSongs.toString()} Songs"
        Glide.with(vh.albumImageView).load(album.albumImgUrl).into(vh.albumImageView)

    }

    fun replaceData(albums: List<Album>) {
        this.albums = albums
        notifyDataSetChanged()
    }

    class AlbumViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var albumImageView: ImageView
        var albumNameTxt: TextView
        var numberOfSongsTxt: TextView

        init {
            albumImageView = view.findViewById(R.id.albumImgView)
            albumNameTxt = view.findViewById(R.id.albumNameTxt)
            numberOfSongsTxt = view.findViewById(R.id.numberOfSongTxt)
        }
    }
}

