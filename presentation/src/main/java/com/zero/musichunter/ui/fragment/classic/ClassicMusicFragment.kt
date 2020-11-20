package com.zero.musichunter.ui.fragment.classic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.zero.musichunter.App
import com.zero.musichunter.R
import com.zero.musichunter.ui.recyclerviewitem.MusicRvItem
import com.zero.musichunter.utils.Extensions.toRecyclerviewListItem
import kotlinx.android.synthetic.main.classic_fragment.*
import javax.inject.Inject

class ClassicMusicFragment : Fragment() {

    @Inject
    lateinit var classicMusicViewModelFactory: ClassicMusicViewModelFactory

    private lateinit var viewModel: ClassicMusicViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.classic_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDagger()

        initViewModel()
    }

    private fun initDagger() {
        (activity?.applicationContext as App).appComponent.activityComponent()
            .create(requireActivity()).inject(this)
    }

    private fun initViewModel() {
        viewModel =
            ViewModelProvider(
                this,
                classicMusicViewModelFactory
            ).get(ClassicMusicViewModel::class.java)

        viewModel.loadClassicRepo()

        viewModel.classicRepo.observe(viewLifecycleOwner) {
            initRecyclerview(it.toRecyclerviewListItem())
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
}