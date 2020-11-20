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
import com.zero.musichunter.ui.recyclerviewitem.MusicRvItem
import com.zero.musichunter.utils.Extensions.toRecyclerviewListItem
import kotlinx.android.synthetic.main.rock_fragment.*
import javax.inject.Inject

class RockMusicFragment : Fragment() {

    @Inject
    lateinit var rockMusicViewModelFactory: RockMusicViewModelFactory
    private lateinit var musicViewModel: RockMusicViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rock_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDagger()

        initViewModel()
    }

    private fun initDagger() {
        (activity?.applicationContext as App).appComponent.activityComponent()
            .create(this.requireActivity()).inject(this)
    }

    private fun initViewModel() {
        musicViewModel =
            ViewModelProvider(
                this,
                rockMusicViewModelFactory
            ).get(RockMusicViewModel::class.java)

        musicViewModel.loadRockRepo()

        musicViewModel.rockRepo.observe(viewLifecycleOwner) {
            initRecyclerview(it.toRecyclerviewListItem())
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

}