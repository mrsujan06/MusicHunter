package com.zero.musichunter.viewModelTest

import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@RunWith(BlockJUnit4ClassRunner::class)
class ClassicMusicViewModelTest {

//    @Rule
//    @JvmField
//    var rule: TestRule = InstantTaskExecutorRule()
//
//    @Inject
//    lateinit var repository: RepoRepository
//
//    @MockK
//    lateinit var classicMusicViewModel: ClassicMusicViewModel
//
//    private var classicMusic: List<RepoDTO> = listOf(
//        RepoDTO("Nirvana", "xcsdec", "pacey", "zed", "come as you are "),
//        RepoDTO("Post Malone", "sdsx", "pdcx", "beer bong", "better now")
//    )
//
//    private var classicMusicz: List<Repo> = listOf(
//        Repo("Nirvana", "xcsdec", "pacey", "zed", "come as you are "),
//        Repo("Post Malone", "sdsx", "pdcx", "beer bong", "better now")
//    )
//
//    private var musicResponse: NetworkMusicContainer = NetworkMusicContainer(2, classicMusic)
//
//
//    @Before
//    fun setup() {
//        MockKAnnotations.init(this)
//
//        val testComponent = DaggerTestAppComponent.builder()
//
//        testComponent.inject(this)
//
//        classicMusicViewModel = ClassicMusicViewModel(repository)
//    }
//
//    @Test
//    fun fetchClassicMusicTest() {
//
//        //given
//        every { repository.getClassicListRepo() } returns (Single.just(classicMusicz))
//
//        //when
//        classicMusicViewModel.fetchClassicFromNet()
//
//        //then
//        Assert.assertEquals(classicMusicViewModel.classicRepoNet.value, musicResponse)
//
//    }


}