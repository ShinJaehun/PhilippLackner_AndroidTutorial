package com.shinjaehun.mvvmdictionary.feature_dictionary.data.repository

import com.shinjaehun.mvvmdictionary.core.util.Resource
import com.shinjaehun.mvvmdictionary.feature_dictionary.data.local.WordInfoDao
import com.shinjaehun.mvvmdictionary.feature_dictionary.data.remote.DictionaryApi
import com.shinjaehun.mvvmdictionary.feature_dictionary.domain.model.WordInfo
import com.shinjaehun.mvvmdictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        //이게 뭘까
        emit(Resource.Loading())

        // 캐싱
        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            // 근데 먼저 이걸 왜 지우는거야?
            dao.deleteWordInfos(remoteWordInfos.map{it.word})
            dao.insertWordInfos(remoteWordInfos.map{it.toWordInfoEntity()})
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "oops, something went wrong!",
                data = wordInfos
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "couldn't reach server",
                data = wordInfos
            ))
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}