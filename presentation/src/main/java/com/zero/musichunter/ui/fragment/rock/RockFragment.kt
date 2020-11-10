package com.zero.musichunter.ui.fragment.rock

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.zero.musichunter.App
import com.zero.musichunter.R
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.repository.RepoRepository
import com.zero.musichunter.ui.recyclerviewitem.MusicRvItem
import com.zero.musichunter.utils.CheckConnection.isOnline
import kotlinx.android.synthetic.main.rock_fragment.*
import javax.inject.Inject

class RockFragment : Fragment() {

    @Inject
    lateinit var repository: RepoRepository
    private lateinit var viewModel: RockViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rock_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity?.applicationContext as App).appComponent.activityComponent()
            .create(this.requireActivity()).inject(this)

        viewModel =
            ViewModelProvider(this, RockViewModelFactory(repository)).get(RockViewModel::class.java)

        if (isOnline(context)) {
            viewModel.fetchRockFromNet()
        } else {
            viewModel.fetchRockFromCache()
        }

        if (isOnline(context)) {
            viewModel.rockRepo.observe(viewLifecycleOwner, {
                initRecyclerview(it.toRecyclerviewListItem())
            })
        } else {

            viewModel.rockRepoCache.observe(viewLifecycleOwner, {
                initRecyclerview(it.toRecyclerviewListItem())
            })
        }
    }

    private fun initRecyclerview(items: List<MusicRvItem>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        rock_music_list.apply {
            adapter = groupAdapter
        }
    }

    private fun List<Repo>.toRecyclerviewListItem(): List<MusicRvItem> {
        return this.map {
            MusicRvItem(it)
        }
    }

}