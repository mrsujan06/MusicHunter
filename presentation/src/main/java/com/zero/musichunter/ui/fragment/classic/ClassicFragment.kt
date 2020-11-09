package com.zero.musichunter.ui.fragment.classic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity?.applicationContext as App).appComponent.activityComponent()
            .create(requireActivity()).inject(this)

        viewModel = ViewModelProvider(
            this,
            ClassicViewModelFactory(repository)
        ).get(ClassicViewModel::class.java)

        viewModel.fetchClassicMusic()

        viewModel.classicRepo.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                initRecyclerview(it.toRecyclerviewListItem())
            } else {
                Toast.makeText(activity, "List size 0", Toast.LENGTH_LONG).show()
            }
        })

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