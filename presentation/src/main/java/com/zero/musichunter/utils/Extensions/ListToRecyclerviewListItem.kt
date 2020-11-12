package com.zero.musichunter.utils.Extensions

import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.ui.recyclerviewitem.MusicRvItem


fun List<Repo>.toRecyclerviewListItem(): List<MusicRvItem> {
    return this.map {
        MusicRvItem(it)
    }
}