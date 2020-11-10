package com.zero.musichunter.ui.fragment.classic

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.zero.musichunter.App
import com.zero.musichunter.R
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.repository.RepoRepository
import com.zero.musichunter.ui.recyclerviewitem.MusicRvItem
import com.zero.musichunter.utils.CheckConnection.isOnline
import kotlinx.android.synthetic.main.classic_fragment.*
import javax.inject.Inject

class ClassicFragment : Fragment() {

    @Inject
    lateinit var repository: RepoRepository

    private lateinit var viewModel: ClassicViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.classic_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity?.applicationContext as App).appComponent.activityComponent()
            .create(requireActivity()).inject(this)

        viewModel = ViewModelProvider(
            this,
            ClassicViewModelFactory(repository)
        ).get(ClassicViewModel::class.java)

        if (isOnline(context)) {
            viewModel.fetchClassicFromNet()
        } else {
            viewModel.fetchClassicFromCache()
        }

        if (isOnline(context)) {
            Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show()
            viewModel.classicRepoNet.observe(viewLifecycleOwner, Observer {
                initRecyclerview(it.toRecyclerviewListItem())
            })
        } else {
            Toast.makeText(context, "No connection", Toast.LENGTH_LONG).show()
            viewModel.classicRepoCache.observe(viewLifecycleOwner) {
                initRecyclerview(it.toRecyclerviewListItem())
            }
        }

    }

    private fun initRecyclerview(items: List<MusicRvItem>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
            notifyDataSetChanged()
        }
        music_list.apply {
            adapter = groupAdapter
        }

    }

    private fun List<Repo>.toRecyclerviewListItem(): List<MusicRvItem> {
        return this.map {
            MusicRvItem(it)
        }
    }



}