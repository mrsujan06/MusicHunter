package com.zero.musichunter.ui.recyclerviewitem

import android.content.Intent
import android.net.Uri
import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import com.zero.musichunter.R
import com.zero.musichunter.domain.model.Repo
import kotlinx.android.synthetic.main.item_music.view.*


class MusicRvItem(private val repo: Repo) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            itemView.artistName.text = repo.artistName
            itemView.trackName.text = repo.trackName

            Picasso.get().load(repo.artworkUrl100)
                .into(viewHolder.itemView.artistImageView)

            itemView.setOnClickListener {
                playMusic(it)
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_music

    private fun playMusic(it: View) {
        val uri = Uri.parse(repo.previewUrl)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setDataAndType(uri, "audio/mpeg3")
        it.context.startActivity(intent)
    }

}