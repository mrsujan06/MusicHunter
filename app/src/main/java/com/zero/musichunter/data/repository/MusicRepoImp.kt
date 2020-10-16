package com.zero.musichunter.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.zero.musichunter.data.database.MusicDatabase
import com.zero.musichunter.data.database.asDomainModel
import com.zero.musichunter.data.database.asDomainPopModel
import com.zero.musichunter.data.database.asDomainRockModel
import com.zero.musichunter.data.domain.MusicResults
import com.zero.musichunter.data.remote.MusicApiService
import com.zero.musichunter.data.remote.asDatabaseModel
import com.zero.musichunter.data.remote.asDatabasePopModel
import com.zero.musichunter.data.remote.asDatabaseRockModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MusicRepoImp @Inject constructor(
    private val musicApiService: MusicApiService,
    private val database: MusicDatabase
) : MusicRepo {

    private val classicMusic: LiveData<List<MusicResults>> = Transformations.map(
        database.musicDao.getClassicMusic()
    ) { it.asDomainModel() }

    private val popMusic: LiveData<List<MusicResults>> = Transformations.map(
        database.musicDao.getPopMusic()
    ) { it.asDomainPopModel() }

    private val rockMusic: LiveData<List<MusicResults>> = Transformations.map(
        database.musicDao.getRockMusic()
    ) { it.asDomainRockModel() }


    override suspend fun getClassicMusic() {
        withContext(Dispatchers.IO) {
            Timber.d("getClassicMusic is called")
            val playlist = musicApiService.getClassicMusic()
            database.musicDao.insertAll(playlist.asDatabaseModel())
        }
    }

    override suspend fun getPopMusic() {
        withContext(Dispatchers.IO) {
            Timber.d("getPopMusic is called")
            val playlist = musicApiService.getPopMusic()
            database.musicDao.insertPopMusic(playlist.asDatabasePopModel())
        }
    }

    override suspend fun getRockMusic() {
        withContext(Dispatchers.IO) {
            Timber.d("getPopMusic is called")
            val playlist = musicApiService.getRockMusic()
            database.musicDao.insertRockMusic(playlist.asDatabaseRockModel())
        }
    }

    override fun classicMusicObservable(): LiveData<List<MusicResults>> = classicMusic

    override fun popMusicObservable(): LiveData<List<MusicResults>> = popMusic

    override fun rockMusicObservable(): LiveData<List<MusicResults>> = rockMusic

}