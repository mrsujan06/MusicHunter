package com.zero.musichunter.ui.fragment.classic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.zero.musichunter.App
import com.zero.musichunter.R
import com.zero.musichunter.data.model.Result
import com.zero.musichunter.data.repository.MusicRepo
import com.zero.musichunter.ui.recyclerviewitem.MusicRvItem
import kotlinx.android.synthetic.main.classic_fragment.*
import javax.inject.Inject

class ClassicFragment : Fragment() {

    @Inject
    lateinit var repository: MusicRepo

    private lateinit var viewModel: ClassicViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.classic_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity?.applicationContext as App).appComponent.inject(this)

        viewModel = ViewModelProvider(
            this,
            ClassicViewModelFactory(repository)
        ).get(ClassicViewModel::class.java)
        viewModel.fetchClassicMusic()

        viewModel.classicMusicObservable.observe(viewLifecycleOwner, Observer {
            initRecyclerview(it.results.toRecyclerviewListItem())
        })

    }

    private fun initRecyclerview(items: List<MusicRvItem>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        music_list.apply {
            adapter = groupAdapter
        }

    }

    private fun List<Result>.toRecyclerviewListItem(): List<MusicRvItem> {
        return this.map {
            MusicRvItem(it)
        }
    }

}