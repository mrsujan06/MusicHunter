package com.zero.musichunter.viewModelTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.data.net.dto.NetworkMusicContainer
import com.example.data.net.dto.RepoDTO
import com.zero.musichunter.data.model.MusicResponse
import com.zero.musichunter.data.model.Result
import com.zero.musichunter.domain.model.Repo
import com.zero.musichunter.domain.repository.RepoRepository
import com.zero.musichunter.testDagger.DaggerTestAppComponent
import com.zero.musichunter.ui.fragment.classic.ClassicViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner
import javax.inject.Inject

@RunWith(BlockJUnit4ClassRunner::class)
class ClassicViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Inject
    lateinit var repository: RepoRepository

    @MockK
    lateinit var classicViewModel: ClassicViewModel

    private var classicMusic: List<RepoDTO> = listOf(
        RepoDTO("Nirvana", "xcsdec", "pacey", "zed", "come as you are "),
        RepoDTO("Post Malone", "sdsx", "pdcx", "beer bong", "better now")
    )

    private var classicMusicz: List<Repo> = listOf(
        Repo("Nirvana", "xcsdec", "pacey", "zed", "come as you are "),
        Repo("Post Malone", "sdsx", "pdcx", "beer bong", "better now")
    )

    private var musicResponse: NetworkMusicContainer = NetworkMusicContainer(2, classicMusic)


    @Before
    fun setup() {
        MockKAnnotations.init(this)

        val testComponent = DaggerTestAppComponent.builder()

        testComponent.inject(this)

        classicViewModel = ClassicViewModel(repository)
    }

    @Test
    fun fetchClassicMusicTest() {

        //given
        every { repository.getClassicListRepo() } returns (Single.just(classicMusicz))

        //when
        classicViewModel.fetchClassicFromNet()

        //then
        Assert.assertEquals(classicViewModel.classicRepoNet.value, musicResponse)

    }


}