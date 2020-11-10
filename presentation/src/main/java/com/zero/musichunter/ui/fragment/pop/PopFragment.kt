package com.zero.musichunter.ui.fragment.pop

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.zero.musichunter.App
import com.zero.musichunter.utils.CheckConnection.isOnline
import com.zero.musichunter.R
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.repository.RepoRepository
import com.zero.musichunter.ui.recyclerviewitem.MusicRvItem
import kotlinx.android.synthetic.main.pop_fragment.*
import javax.inject.Inject

class PopFragment : Fragment() {

    @Inject
    lateinit var repository: RepoRepository

    private lateinit var viewModel: PopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pop_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity?.applicationContext as App).appComponent.activityComponent()
            .create(this.requireActivity()).inject(this)

        viewModel =
            ViewModelProvider(this, PopViewModelFactory(repository)).get(PopViewModel::class.java)

        if (isOnline(context)) {
            viewModel.fetchPopFromNet()
        } else{
            viewModel.fetchPopFromCache()
        }

        if (isOnline(context)){
            viewModel.popRepo.observe(viewLifecycleOwner, Observer {
                initRecyclerview(it.toRecyclerviewListItem())
            })
        } else {
            viewModel.popRepoCache.observe(viewLifecycleOwner, Observer {
                initRecyclerview(it.toRecyclerviewListItem())
            })
        }

    }

    private fun initRecyclerview(items: List<MusicRvItem>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        pop_music_list.apply {
            adapter = groupAdapter
        }

    }

    private fun List<Repo>.toRecyclerviewListItem(): List<MusicRvItem> {
        return this.map {
            MusicRvItem(it)
        }
    }

}