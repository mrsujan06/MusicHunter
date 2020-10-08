package com.zero.musichunter.viewModelTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zero.musichunter.data.repository.MusicRepo
import com.zero.musichunter.testDagger.DaggerTestAppComponent
import com.zero.musichunter.testDagger.TestAppModule
import com.zero.musichunter.ui.fragment.classic.ClassicViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
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
    lateinit var repository: MusicRepo

    @MockK
    lateinit var classicViewModel: ClassicViewModel

    private var classicMusic: List<Result> = listOf(Result("Nirvana" , "xcsdec" , "pxyyy","zed", "come as you are ")
    , Result("Post Malone", "sdsx" , "pdcx" , "beer bong" , "better now")
    )

    private var musicResponse: MusicResponse = MusicResponse(2 , classicMusic)


    @Before
    fun setup(){
        MockKAnnotations.init(this)

        val testComponent = DaggerTestAppComponent.builder()
            .appModule(TestAppModule())
            .build()

        testComponent.inject(this)

        classicViewModel = ClassicViewModel(repository)
    }

    @Test
    fun fetchClassicMusicTest(){

        //given
        every{repository.getClassicMusic()} returns (Observable.just(musicResponse))

        //when
        classicViewModel.fetchClassicMusic()

        //then
        Assert.assertEquals(classicViewModel.classicMusic.value, musicResponse)

    }


}